package dev.vultureweb.vaardagen.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

public record Content(String path, byte[] raw, byte[] gzip, String mimeType, boolean cacheable,boolean gzipWorthwhile, long lastModified, String hash) {

  public static Content buildContent(String path, byte[] raw, ServletContext servletContext) throws ServletException {
    String mimeType = determineMimeType(path, servletContext);
    final byte[] gzip = gzip(raw);
    final int percentage = raw.length == 0 ? 0 : (gzip.length * 100) / raw.length;
    final boolean gzipWorthwhile = raw.length >= 1000 && percentage <= 80;
    final long lastModified = System.currentTimeMillis();
    final boolean cacheable = isCacheable(mimeType);
    final String hash = calculateHash(raw);
    return new Content(path, raw, gzip, mimeType, cacheable, gzipWorthwhile, lastModified, hash);
  }

  private static String determineMimeType(String path, ServletContext servletContext) throws ServletException {
    final String mimeType = servletContext.getMimeType(path);
    if (mimeType == null) {
      if (path.endsWith(".map")) {
        return "application/json";
      }
      throw new ServletException("Invalid mime type: " + path);
    } else {
      return mimeType;
    }
  }

  private static boolean isCacheable(String mimeType) throws ServletException {
    return switch (mimeType) {
      case "image/jpeg",
           "application/javascript",
           "application/x-font-tif",
           "application/font-woff",
           "application/font-woff2",
           "font/woff",
           "image/x-icon",
           "image/svg+xml",
           "image/png",
           "text/css" -> true;
      case "text/plain",
           "text/html" -> false;
      default -> throw new ServletException("Invalid mime type: " + mimeType);
    };
  }

  private static byte[] gzip(byte[] data) {

    try (final ByteArrayOutputStream out = new ByteArrayOutputStream(data.length)) {
      try (BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
           final BufferedOutputStream bos = new BufferedOutputStream(new GZIPOutputStream(out))) {
        final byte[] buffer = new byte[0x1000];
        int read;
        while ((read = in.read(buffer)) != -1) {
          bos.write(buffer, 0, read);
        }
        return out.toByteArray();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String calculateHash(byte[] data) {
    try{
      byte[] digest = MessageDigest.getInstance("SHA-256").digest(data);
      return Base64.getEncoder().encodeToString(digest);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new UnsupportedOperationException(noSuchAlgorithmException.getMessage(),noSuchAlgorithmException);
    }
  }
}
