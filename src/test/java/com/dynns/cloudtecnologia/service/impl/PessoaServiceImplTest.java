package com.dynns.cloudtecnologia.service.impl;

import com.dynns.cloudtecnologia.exception.EntidadeNaoEncontradaException;
import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.rest.client.ViaCepClient;

import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.mockito.ArgumentMatchers.any;
import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaServiceImplTest {

    @Inject
    private ObjectMapper objectMapper;


    private static final String CPF_EXISTENTE = "89626778490";


    @Inject
    @InjectMock
    @RestClient
    private ViaCepClient viaCepClientMock;

    @Inject
    private PessoaServiceImpl pessoaService;

    private static final Logger LOG = LoggerFactory.getLogger(PessoaServiceImplTest.class);

    @Test
    @DisplayName("Deve Buscar Endereco Pelo Cpf")
    @Order(1)
    void deveBuscarEnderecoPeloCpf() {

        String cep = "74913330";

        EnderecoDTO enderecoDTO = getEnderecoDTO();
        Mockito.when(viaCepClientMock.getEnderecoByCep(any())).thenReturn(enderecoDTO);

        EnderecoDTO end = pessoaService.buscarEnderecoPessoaPorCpf(CPF_EXISTENTE, cep);
        LOG.info(end.toString());

        assertNotNull(end);
        assertEquals("cep", end.getCep());

    }


    @Test
    @DisplayName("Deve listar Pessoas Service")
    @Order(2)
    void deveListarService() {

        List<Pessoa> lista  = pessoaService.listar();
        LOG.info(lista.toString());

        assertNotNull(lista);

    }

    @Test
    @DisplayName("Deve Buscar Por CpfThrow Service")
    @Order(5)
    void deveBuscarPorCpfThrowService() {
        String cpfInvalido = "04945608750";
        assertThrows(EntidadeNaoEncontradaException.class, () -> pessoaService.buscarPorCpfOrThrow(cpfInvalido));
    }

    private EnderecoDTO getEnderecoDTO() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setSiafi("siafi");
        enderecoDTO.setDdd("ddd");
        enderecoDTO.setUf("uf");
        enderecoDTO.setCep("cep");
        enderecoDTO.setGia("gia");
        enderecoDTO.setBairro("bairro");
        enderecoDTO.setComplemento("compl");
        enderecoDTO.setLocalidade("loc");
        enderecoDTO.setIbge("ibge");


        return enderecoDTO;
    }

}