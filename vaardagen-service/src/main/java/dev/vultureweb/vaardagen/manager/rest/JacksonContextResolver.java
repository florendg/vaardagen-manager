package dev.vultureweb.vaardagen.manager.rest;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {

  private final ObjectMapper objectMapper;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public JacksonContextResolver() {
    var javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(javaTimeModule);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return objectMapper;
  }
}
