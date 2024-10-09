package dev.vultureweb.vaardagen.manager.rest;

import dev.vultureweb.vaardagen.manager.persistence.TripTableManagerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TripTableManagerExceptionMapper implements ExceptionMapper<TripTableManagerException> {
  @Override
  public Response toResponse(TripTableManagerException e) {
    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(e.getMessage())
        .build();
  }
}
