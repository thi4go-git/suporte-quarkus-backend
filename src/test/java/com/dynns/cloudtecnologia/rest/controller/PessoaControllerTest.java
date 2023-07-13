package com.dynns.cloudtecnologia.rest.controller;


import com.dynns.cloudtecnologia.rest.dto.PessoaDTONew;
import com.dynns.cloudtecnologia.rest.dto.PessoaDTOUpdate;
import com.dynns.cloudtecnologia.rest.mapper.PessoaMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(PessoaController.class)
class PessoaControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(PessoaControllerTest.class);

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private PessoaMapper pessoaMapper;

    private static final String NOVA_PESSOA = "{\n" +
            "    \"nome\": \"Daiane Tereza Oliveira\",\n" +
            "    \"cpf\": \"89626778490\",\n" +
            "    \"cep\": \"74913330\",\n" +
            "    \"telefone\": \"6232845603\",\n" +
            "    \"email\": \"felipeluisassis@danielsalla.com\",\n" +
            "    \"tituloEleitoral\": \"854056551465\",\n" +
            "    \"nacionalidade\": \"BRASILEIRO\"\n" +
            "}";


    private static final String CPF_INEXISTENTE = "00626778400";
    private static final String CPF_EXISTENTE = "89626778490";
    private static final Long ID_EXISTENTE = 2L;
    private static final Long ID_INEXISTENTE = 999999L;
    private static final String NOME_UPDATE = "TESTE UPDATE";
    private static final String TAG_ERRORS = "errors";

    private static final String MENSAGEM_ID_INEXISTENTE_ESPERADA = "[Entidade: 'Pessoa', id '" + ID_INEXISTENTE + "' Não localizada! ]";


    @Test
    @DisplayName("Deve salvar uma Pessoa com Sucesso")
    @Order(1)
    void salvarSucesso() throws JsonProcessingException {

        PessoaDTONew pessoaDTONew = objectMapper.readValue(NOVA_PESSOA, PessoaDTONew.class);

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pessoaDTONew)
                .when()
                .post()
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertNotNull(responseBody);
        assertEquals(HttpStatus.SC_CREATED, resposta.statusCode());
        assertEquals(pessoaDTONew.getNome(), resposta.jsonPath().getString("nome"));
        assertEquals(pessoaDTONew.getCpf(), resposta.jsonPath().getString("cpf"));
        assertEquals(pessoaDTONew.getCep(), resposta.jsonPath().getString("cep"));
        assertEquals(pessoaDTONew.getTelefone(), resposta.jsonPath().getString("telefone"));
        assertEquals(pessoaDTONew.getEmail(), resposta.jsonPath().getString("email"));
        assertEquals(pessoaDTONew.getTituloEleitoral(), resposta.jsonPath().getString("tituloEleitoral"));
        assertEquals(pessoaDTONew.getNacionalidade().toString(), resposta.jsonPath().getString("nacionalidade"));
    }


    @Test
    @DisplayName("Não deve salvar uma Pessoa com CPF inválido")
    @Order(2)
    void salvarErro() throws JsonProcessingException {

        PessoaDTONew pessoaDTONew = objectMapper.readValue(NOVA_PESSOA, PessoaDTONew.class);
        pessoaDTONew.setNome("Teste");
        pessoaDTONew.setCpf("04945");
        pessoaDTONew.setTituloEleitoral("462411670124");

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pessoaDTONew)
                .when()
                .post()
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        String mensagemEsperada = "[CPF Inválido!]";
        String mensagemRetorno = resposta.jsonPath().getString(TAG_ERRORS).toString();


        assertEquals(HttpStatus.SC_BAD_REQUEST, resposta.statusCode());
        assertEquals(mensagemEsperada, mensagemRetorno);

    }


    @Test
    @DisplayName("Deve Listar Pessoas com sucesso")
    @Order(3)
    void listar() {

        String token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJc25GYzJwXzZvR19pTHAtVlNmdUdJaXppX1ZNYnROOGNlc0s3bjI2Q2NNIn0.eyJleHAiOjE2ODkxMzcxMDYsImlhdCI6MTY4OTEzNTM2NiwianRpIjoiZmRjNmM2ZDYtZDU2NC00ZmYyLTk4ODYtNGIwN2E2YWI1NTgwIiwiaXNzIjoiaHR0cDovL2Nsb3VkdGVjbm9sb2dpYS5keW5ucy5jb206ODE4MC9yZWFsbXMvQ0xPVURfVEVDTk9MT0dJQSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIzODUyZTNhYS00ZjI1LTQwM2ItODdiYS02MmQ0MGEwOGNmZWEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJzdXBvcnRlLXF1YXJrdXMiLCJzZXNzaW9uX3N0YXRlIjoiNDc0YTEyMDMtYzBiMi00NTlmLTg0MTEtMDA0NDA1YTJlY2NjIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLWNsb3VkX3RlY25vbG9naWEiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwidXNlciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjQ3NGExMjAzLWMwYjItNDU5Zi04NDExLTAwNDQwNWEyZWNjYyIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IlRoaWFnbyBNZWxvIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidGhpYWdvLm1lbG8iLCJnaXZlbl9uYW1lIjoiVGhpYWdvIiwiZmFtaWx5X25hbWUiOiJNZWxvIiwiZW1haWwiOiJ0aGk0Z28xOUBnbWFpbC5jb20ifQ.skWZEWp1DxzLvGmDFIccr-wERydRF-aapyaM2pktcjJ4d4TEvA__yxx4-RLZxb0VejWOyfO1OCl7gNXPOZMS1ptlvMxbUcy7vQGXtoAtxdvq6cyZ2s_utqTGf_AlnZrwslk6LO8wFdtkPgMGUNsfaR9xo1pomTQNpCMwAXnqy_GU0A9WqnCQejbpboMHmZP4yb_98nDkCF60DegQxxrQIqsFclOnaBppoRAmoR0YRQkbMglR45D3Oj7VYS_2AIrdMXO34OIEqOldB3Az3NHlMgIvOYgK2Cd9f1I5spFRv8GmiUmjrnNAzRTS6Rl0EwT-ItNp3X3_bhvxFcbMkxV2vA";

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get()
                .then()
                .body("size()", Matchers.is(2))
                .extract().response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertNotNull(responseBody);
        assertEquals(HttpStatus.SC_OK, resposta.statusCode());
    }

    @Test
    void testListar(){
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get()
                .then()
                ;
    }

    @Test
    @DisplayName("Deve Obter uma pessoa pelo CPF")
    @Order(4)
    void buscarPorCpfSucesso() {

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("cpf", CPF_EXISTENTE)
                .when()
                .get("/{cpf}")
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertEquals(HttpStatus.SC_OK, resposta.statusCode());
        assertNotNull(responseBody);
        assertEquals(CPF_EXISTENTE, resposta.jsonPath().getString("cpf"));

    }


    @Test
    @DisplayName("Deve lançar EntidadeNaoEncontradaExceptionMapper ao buscar uma pessoa pelo CPF inválido")
    @Order(5)
    void buscarPorCpfErro() {

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("cpf", CPF_INEXISTENTE)
                .when()
                .get("/{cpf}")
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        String mensagemEsperada = "[Entidade: 'Pessoa', cpf '" + CPF_INEXISTENTE + "' Não localizada! ]";
        String mensagemRetorno = resposta.jsonPath().getString(TAG_ERRORS).toString();

        assertEquals(HttpStatus.SC_NOT_FOUND, resposta.statusCode());
        assertEquals(mensagemEsperada, mensagemRetorno);

    }

    @Test
    @DisplayName("Deve Atualizar Pessoa com sucesso")
    @Order(6)
    void atualizarSucesso() throws JsonProcessingException {

        PessoaDTONew pessoaDTONew = objectMapper.readValue(NOVA_PESSOA, PessoaDTONew.class);
        PessoaDTOUpdate pessoaDTOUpdate = pessoaMapper.pessoaDTONewToPessoaDTOUpdate(pessoaDTONew);

        pessoaDTOUpdate.setId(ID_EXISTENTE);
        pessoaDTOUpdate.setNome(NOME_UPDATE);

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pessoaDTOUpdate)
                .when()
                .put()
                .then()
                .extract()
                .response();


        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertNotNull(responseBody);
        assertEquals(HttpStatus.SC_OK, resposta.statusCode());
        assertEquals(NOME_UPDATE, resposta.jsonPath().getString("nome"));
        assertEquals(pessoaDTONew.getCpf(), resposta.jsonPath().getString("cpf"));
        assertEquals(pessoaDTONew.getCep(), resposta.jsonPath().getString("cep"));
        assertEquals(pessoaDTONew.getTelefone(), resposta.jsonPath().getString("telefone"));
        assertEquals(pessoaDTONew.getEmail(), resposta.jsonPath().getString("email"));
        assertEquals(pessoaDTONew.getTituloEleitoral(), resposta.jsonPath().getString("tituloEleitoral"));
        assertEquals(pessoaDTONew.getNacionalidade().toString(), resposta.jsonPath().getString("nacionalidade"));


    }


    @Test
    @DisplayName("Deve Lançar EntidadeNaoEncontradaExceptionMapper ao atualziar com id inexistente")
    @Order(7)
    void atualizarErro() throws JsonProcessingException {

        PessoaDTONew pessoaDTONew = objectMapper.readValue(NOVA_PESSOA, PessoaDTONew.class);
        PessoaDTOUpdate pessoaDTOUpdate = pessoaMapper.pessoaDTONewToPessoaDTOUpdate(pessoaDTONew);

        pessoaDTOUpdate.setId(ID_INEXISTENTE);
        pessoaDTOUpdate.setNome(NOME_UPDATE);

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pessoaDTOUpdate)
                .when()
                .put()
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        String mensagemRetorno = resposta.jsonPath().getString(TAG_ERRORS).toString();

        assertEquals(HttpStatus.SC_NOT_FOUND, resposta.statusCode());
        assertEquals(MENSAGEM_ID_INEXISTENTE_ESPERADA, mensagemRetorno);

    }

    @Test
    @DisplayName("Deve Listar Pessoas listarReflection com sucesso")
    @Order(8)
    void listarReflection() {

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/reflection")
                .then()
                .body("size()", Matchers.is(2))
                .extract().response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertNotNull(responseBody);
        assertEquals(HttpStatus.SC_OK, resposta.statusCode());
    }


    @Test
    @DisplayName("Deve chamar Buscar Endereco")
    @Order(9)
    void deveChamarBuscarEnderecoPorCpf() {

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("cpf", CPF_EXISTENTE)
                .when()
                .get("/{cpf}/endereco")
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertNotNull(responseBody);

    }

    @Test
    @DisplayName("Deve deletar Pessoa pelo ID")
    @Order(10)
    void deletarPessoaPorIdSucesso() {

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("idPessoa", ID_EXISTENTE)
                .when()
                .delete("/{idPessoa}")
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        assertEquals(HttpStatus.SC_NO_CONTENT, resposta.statusCode());

    }

    @Test
    @DisplayName("Deve Lançar EntidadeNaoEncontradaExceptionMapper ao deletar Pessoa pelo ID inexistente")
    @Order(11)
    void deletarPessoaPorIdErro() {

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("idPessoa", ID_INEXISTENTE)
                .when()
                .delete("/{idPessoa}")
                .then()
                .extract()
                .response();

        String responseBody = resposta.getBody().asString();
        LOG.info(responseBody);

        String mensagemRetorno = resposta.jsonPath().getString(TAG_ERRORS).toString();

        assertEquals(HttpStatus.SC_NOT_FOUND, resposta.statusCode());
        assertEquals(MENSAGEM_ID_INEXISTENTE_ESPERADA, mensagemRetorno);

    }

}