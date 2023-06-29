package com.dynns.cloudtecnologia.exception.mapper;

import com.dynns.cloudtecnologia.exception.EntidadeNaoEncontradaException;
import com.dynns.cloudtecnologia.rest.dto.ApplicationErrorsDTO;
import org.jboss.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class EntidadeNaoEncontradaExceptionMapper  implements ExceptionMapper<EntidadeNaoEncontradaException> {

    private static final Logger LOGGER = Logger.getLogger(EntidadeNaoEncontradaExceptionMapper.class);

    @Override
    public Response toResponse(EntidadeNaoEncontradaException exception) {
        LOGGER.info(exception.getMessage());
        List<String> erros = new ArrayList<>();
        erros.add(exception.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ApplicationErrorsDTO(erros))
                .build();
    }
}
