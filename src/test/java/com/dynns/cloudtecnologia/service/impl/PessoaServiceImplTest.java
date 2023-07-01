package com.dynns.cloudtecnologia.service.impl;

import com.dynns.cloudtecnologia.rest.client.ViaCepClient;

import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.mockito.ArgumentMatchers.any;
import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PessoaServiceImplTest {

    @Inject
    @InjectMock
    @RestClient
    private ViaCepClient viaCepClientMock;

    @Inject
    private PessoaServiceImpl pessoaService;

    private static final Logger LOG = LoggerFactory.getLogger(PessoaServiceImplTest.class);

    @Test
    @DisplayName("Deve Buscar Endereco Pelo Cpf")
    void deveBuscarEnderecoPeloCpf() {

        String cpf = "45454545454";
        String cep = "5454545";

        EnderecoDTO enderecoDTO = getEnderecoDTO();
        Mockito.when(viaCepClientMock.getEnderecoByCep(any())).thenReturn(enderecoDTO);

        EnderecoDTO end = pessoaService.buscarEnderecoPessoaPorCpf(cpf, cep);
        LOG.info(end.toString());

        assertNotNull(end);
        assertEquals("cep", end.getCep());

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