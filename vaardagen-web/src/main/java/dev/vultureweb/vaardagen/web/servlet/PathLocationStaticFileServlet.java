package dev.vultureweb.vaardagen.web.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PathLocationStaticFileServlet extends HttpServlet {

  private static final System.Logger LOGGER = System.getLogger(PathLocationStaticFileServlet.class.getName());
  private final Map<String, Content> cache = new HashMap<>();

  private String root;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    root = determineRoot(config);
    final var paths = config.getServletContext().getResourcePaths(root);
    LOGGER.log(System.Logger.Level.INFO, "Servlet resources paths: {0}", paths);
    paths.stream()
      .map(path -> toContent(path,config))
      .filter(Objects::nonNull)
      .forEach(content -> cache.put(content.path(),content));
  }

  private String determineRoot(ServletConfig config) {
    final Set<ServletRegistration> registrations = config.getServletContext().getServletRegistrations()
      .values().stream().filter(r -> r.getClassName().equals(getClass().getName()))
      .collect(Collectors.toSet());
    assert(registrations.size() == 1);
    final ServletRegistration registration = registrations.iterator().next();
    final Collection<String> mappings = registration.getMappings();
    var mapping = mappings.iterator().next();
    if(mapping.startsWith("/") && mapping.endsWith("/*")) {
      mapping = mapping.substring(0, mapping.length()-1);
    }
    return mapping;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    final String key = determineKey(request);
    final Content content = cache.get(key);
    if(content == null) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    } else {
      final boolean gzipped = false;
      response.setHeader("Content-Type",content.mimeType());
      if(content.cacheable()) {
        response.setDateHeader("Last-Modified", System.currentTimeMillis());
        response.setHeader("ETag", content.hash() + (gzipped ? "g" : ""));
        response.setHeader("Cache-Control", "max-age=7200");
      } else {
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
      }
      if(gzipped) {
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Content-Length", String.valueOf(content.gzip().length));
        BufferedOutputStream bufferedOutput = new  BufferedOutputStream(response.getOutputStream());
        bufferedOutput.write(content.gzip());
        bufferedOutput.flush();
      } else {
        response.setHeader("Content-Length", String.valueOf(content.raw().length));
        BufferedOutputStream bufferedOutput = new  BufferedOutputStream(response.getOutputStream());
        bufferedOutput.write(content.raw());
        bufferedOutput.flush();
      }
    }
  }

  private boolean acceptsGzip(HttpServletRequest request) {
    for(Enumeration<String> e = request.getHeaders("Accept-Encoding"); e.hasMoreElements(); ) {
      if(!e.nextElement().equals("gzip")) {
        return true;
      }
    }
    return false;
  }

  private Content toContent(String path, ServletConfig config) {
    try(final InputStream is = config.getServletContext().getResourceAsStream(path)) {
      if(is == null) {
        final var paths = config.getServletContext().getResourcePaths(path);
        LOGGER.log(System.Logger.Level.INFO, "Servlet resources paths in {0}: {1}",path, paths);
        paths.stream()
          .map(p -> toContent(p,config))
          .filter(Objects::nonNull)
          .forEach(content -> cache.put(content.path(),content));
        return null;
      }
      byte[] raw = inputStreamToByteArray(is);
      return Content.buildContent(path, raw, getServletContext());
    }catch (IOException | ServletException exception) {
      LOGGER.log(System.Logger.Level.ERROR, exception.getMessage(), exception);
      return null;
    }
  }

  private byte[] inputStreamToByteArray(InputStream is) throws IOException {
    byte[] buffer = new byte[4096];
    int len;
    try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
      while ((len = is.read(buffer)) != -1) {
        bos.write(buffer, 0, len);
      }
      bos.flush();
      return bos.toByteArray();
    }
  }

  private String determineKey(HttpServletRequest request) {
    var path = request.getRequestURI().substring(request.getContextPath().length());
    if(path.equals(root)) {
      return  getIndexPath();
    } else  {
      return path;
    }
  }

  private String getIndexPath() {
    return root + "index.html";
  }

}
