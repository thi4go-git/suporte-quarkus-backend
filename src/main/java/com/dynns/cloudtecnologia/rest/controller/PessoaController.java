package com.dynns.cloudtecnologia.rest.controller;

import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.rest.dto.*;
import com.dynns.cloudtecnologia.rest.mapper.PessoaMapper;
import com.dynns.cloudtecnologia.service.impl.MensagemSwaggerService;
import com.dynns.cloudtecnologia.service.impl.PessoaServiceImpl;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import org.eclipse.microprofile.openapi.annotations.security.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/api/pessoas")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pessoa Controller", description = "API de Pessoas")//Swagger
@SecuritySchemes({ //Autenticar com Token no Swagger
        @SecurityScheme(
                securitySchemeName = "pessoa-controller-oauth",
                type = SecuritySchemeType.OAUTH2,
                flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://cloudtecnologia.dynns.com:8180/realms/CLOUD_TECNOLOGIA/protocol/openid-connect/token"))
        )
})
@SecurityRequirement(name = "pessoa-controller-oauth")// configura o envio do token nas requisições
public class PessoaController {

    @Inject
    private PessoaServiceImpl pessoaService;

    @Inject
    private PessoaMapper pessoaMapper;


    @POST
    @RequestBody(required = true)//Swagger
    @Operation(summary = "Cria uma Pessoa")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_CREATED, description = MensagemSwaggerService.MSG_SUCESSO, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PessoaDTONew.class))),})
    public Response salvar(@RequestBody(description = MensagemSwaggerService.MSG_DTO_CREATE, required = true, content = @Content(schema = @Schema(implementation = PessoaDTONew.class))) @Valid final PessoaDTONew pessoaDTONew) {
        Pessoa nova = pessoaService.salvar(pessoaDTONew);
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(pessoaMapper.pessoaToPessoaDTOView(nova)).build();
    }


    @GET
    @Operation(summary = "Listagem de Pessoas")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_OK, description = MensagemSwaggerService.MSG_SUCESSO, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PessoaDTOView.class))), @APIResponse(responseCode = MensagemSwaggerService.STATUS_NOT_FOUND, description = MensagemSwaggerService.MSG_SEM_REGISTROS, content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response listar() {
        List<Pessoa> pessoaList = pessoaService.listar();
        return Response.ok(pessoaMapper.listPessoaToListPessoaDTOView(pessoaList)).build();
    }

    @GET
    @Path("/{cpf}")
    @Operation(summary = "Busca Pessoa pelo cpf")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_OK, description = MensagemSwaggerService.MSG_SUCESSO, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PessoaDTOView.class))), @APIResponse(responseCode = MensagemSwaggerService.STATUS_NOT_FOUND, description = MensagemSwaggerService.MSG_SEM_REGISTROS, content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response buscarPorCpf(@PathParam("cpf") @Parameter(required = true, schema = @Schema(ref = "cpf")) @NotBlank(message = "cpf é obrigatório!") final String cpf) {
        Pessoa pessoa = pessoaService.buscarPorCpfOrThrow(cpf);
        return Response.ok(pessoaMapper.pessoaToPessoaDTOView(pessoa)).build();
    }

    @PUT
    @RequestBody(required = true)
    @Operation(summary = "Atualiza uma Pessoa")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_OK, description = MensagemSwaggerService.MSG_ATUALIZADO, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PessoaDTOView.class))), @APIResponse(responseCode = MensagemSwaggerService.STATUS_NOT_FOUND, description = MensagemSwaggerService.MSG_SEM_REGISTROS, content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response atualizar(@RequestBody(description = MensagemSwaggerService.MSG_DTO_UPDATE, required = true, content = @Content(schema = @Schema(implementation = PessoaDTOUpdate.class))) @Valid final PessoaDTOUpdate pessoaDTOUpdate) {
        Pessoa nova = pessoaService.atualizar(pessoaDTOUpdate);
        return Response.ok(pessoaMapper.pessoaToPessoaDTOView(nova)).build();
    }


    @GET
    @Path("/reflection")
    @Operation(summary = "Listagem de Pessoas com campos específicos")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_OK, description = MensagemSwaggerService.MSG_SUCESSO, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PessoaReflectionDTO.class))), @APIResponse(responseCode = MensagemSwaggerService.STATUS_NOT_FOUND, description = MensagemSwaggerService.MSG_SEM_REGISTROS, content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response listarReflection() {
        return Response.ok(pessoaService.getPessoasReflection()).build();
    }

    @GET
    @Path("/{cpf}/endereco")
    @Operation(summary = "Busca endereço da Pessoa pelo cpf")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_OK, description = MensagemSwaggerService.MSG_SUCESSO, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PessoaEnderecoDTO.class))), @APIResponse(responseCode = MensagemSwaggerService.STATUS_NOT_FOUND, description = MensagemSwaggerService.MSG_SEM_REGISTROS, content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response buscarEnderecoPorCpf(@PathParam("cpf") @Parameter(required = true, schema = @Schema(ref = "cpf")) @NotBlank(message = "cpf é obrigatório!") final String cpf) {
        Pessoa pessoa = pessoaService.buscarPorCpfOrThrow(cpf);
        EnderecoDTO enderecoDTO = pessoaService.buscarEnderecoPessoaPorCpf(cpf, pessoa.getCep());

        return Response.ok(pessoaMapper.pessoaDTOViewAndEnderetoDtoToPessoaEnderecoDTO(pessoa, enderecoDTO)).build();
    }

    @DELETE
    @Path("/{idPessoa}")
    @Operation(summary = "Deleta pessoa pelo ID")
    @APIResponses(value = {@APIResponse(responseCode = MensagemSwaggerService.STATUS_NO_CONTENT, description = MensagemSwaggerService.MSG_DELETADO, content = @Content(mediaType = MediaType.APPLICATION_JSON)), @APIResponse(responseCode = MensagemSwaggerService.STATUS_NOT_FOUND, description = MensagemSwaggerService.MSG_SEM_REGISTROS, content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response deletarPessoaPorId(@PathParam("idPessoa") @Parameter(required = true, schema = @Schema(ref = "idPessoa")) @NotNull(message = "idPessoa é obrigatório!") final Long idPessoa) {
        pessoaService.deletar(idPessoa);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
