package com.dynns.cloudtecnologia.model.repository;

import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.model.enums.NacionalidadeEnum;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;



@QuarkusTest
class PessoaRepositoryTest {

    @Inject
    PessoaRepository pessoaRepository;

    private static String CPF_EXISTENTE = "06500485064";

    @Test
    @Transactional
    void deveSalvarPessoaRepository() {
        Pessoa pessoa = getNewPessoa();
        pessoaRepository.persist(pessoa);
        assertNotNull(pessoaRepository.findByCpf(CPF_EXISTENTE));
    }

    @Test
    void findByTituloEleitoral() {
        assertNotNull(pessoaRepository.findByTituloEleitoral("123456789123"));
    }


    private Pessoa getNewPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(CPF_EXISTENTE);
        pessoa.setNome("Teste");
        pessoa.setNacionalidade(NacionalidadeEnum.ESTRANGEIRO);
        pessoa.setTituloEleitoral("123456789123");
        pessoa.setEmail("mail@gmail.com");
        pessoa.setTelefone("6235489050");
        pessoa.setCep("74954550");
        pessoa.setAtivo(true);
        pessoa.setDataCadastro(LocalDate.now());

        return pessoa;
    }

}