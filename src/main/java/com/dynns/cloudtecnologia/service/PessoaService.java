package com.dynns.cloudtecnologia.service;

import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;
import com.dynns.cloudtecnologia.rest.dto.PessoaDTONew;
import com.dynns.cloudtecnologia.rest.dto.PessoaDTOUpdate;
import com.dynns.cloudtecnologia.rest.dto.PessoaReflectionDTO;

import java.util.List;

public interface PessoaService {
    Pessoa salvar(PessoaDTONew pessoaDTONew);

    List<Pessoa> listar();

    Pessoa buscarPorCpf(String cpf);

    Pessoa buscarPorCpfOrThrow(String cpf);

    Pessoa buscarPorTituloEleitoral(String tituloEleitoral);

    EnderecoDTO buscarEnderecoPessoaPorCpf(String cpf, String cep);

    boolean cepIsValido(String cep);

    Pessoa atualizar(PessoaDTOUpdate pessoaDTOUpdate);

    boolean deletar(Long id);

    List<PessoaReflectionDTO> getPessoasReflection();

    void enviarPessoaKafka(PessoaDTONew pessoaDTONew);

}
