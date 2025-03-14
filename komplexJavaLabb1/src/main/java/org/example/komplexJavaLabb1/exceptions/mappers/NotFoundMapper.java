package org.example.komplexJavaLabb1.exceptions.mappers;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.komplexJavaLabb1.exceptions.NotFound;
import jakarta.ws.rs.core.MediaType;


@Provider
public class NotFoundMapper implements ExceptionMapper<NotFound> {

    @Override
    public Response toResponse(NotFound notFound) {
        return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(notFound.getMessage())
                        .build();
    }
}
