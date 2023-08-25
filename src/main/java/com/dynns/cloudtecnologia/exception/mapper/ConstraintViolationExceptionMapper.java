package com.dynns.cloudtecnologia.exception.mapper;

import com.dynns.cloudtecnologia.rest.dto.ApplicationErrorsDTO;
import org.jboss.logging.Logger;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;


@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOGGER = Logger.getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOGGER.info(exception.getMessage());
        List<String> erros = new ArrayList<>();
        for (ConstraintViolation<?> exceptions : exception.getConstraintViolations()) {
            erros.add(exceptions.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApplicationErrorsDTO(erros))
                .build();
    }
}
