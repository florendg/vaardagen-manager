package dev.vultureweb.vaardagen.manager.rest.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegaArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
  @Override
  public Response toResponse(IllegalArgumentException e) {
    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(e.getMessage())
        .build();
  }
}
