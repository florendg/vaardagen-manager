package dev.vultureweb.vaardagen.web.servlet;

import jakarta.servlet.Registration;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PathLocationStaticFileServlet extends HttpServlet {

  private static final System.Logger LOGGER = System.getLogger(PathLocationStaticFileServlet.class.getName());

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
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
    final var paths = config.getServletContext().getResourcePaths(mapping);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // super.doGet(req, resp);
    LOGGER.log(System.Logger.Level.INFO, "PathLocationStaticFileServlet GET");
  }
}
