package com.dynns.cloudtecnologia.rest.client;

import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ViaCepClient {

    @GET
    @Path("/{cep}/json")
    EnderecoDTO getEnderecoByCep(@PathParam("cep") String cep);


}
