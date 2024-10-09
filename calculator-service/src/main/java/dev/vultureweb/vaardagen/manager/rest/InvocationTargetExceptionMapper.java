package dev.vultureweb.vaardagen.manager.rest;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.lang.reflect.InvocationTargetException;

@Provider
public class InvocationTargetExceptionMapper implements ExceptionMapper<InvocationTargetException> {
  @Override
  public Response toResponse(InvocationTargetException e) {
    return Response.status(Response.Status.BAD_REQUEST)
        .entity(e.getCause()).build();
  }
}
