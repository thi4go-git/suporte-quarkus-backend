package com.dynns.cloudtecnologia.rest.client;

import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ViaCepClient {

    @GET
    @Path("/{cep}/json")
    public EnderecoDTO getEnderecoByCep(@PathParam("cep") String cep);


}
