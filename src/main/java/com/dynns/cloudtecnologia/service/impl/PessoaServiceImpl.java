package com.dynns.cloudtecnologia.service.impl;


import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.model.repository.PessoaRepository;
import com.dynns.cloudtecnologia.rest.client.ViaCepClient;
import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;
import com.dynns.cloudtecnologia.rest.dto.PessoaDTONew;
import com.dynns.cloudtecnologia.rest.dto.PessoaDTOUpdate;
import com.dynns.cloudtecnologia.rest.dto.PessoaReflectionDTO;
import com.dynns.cloudtecnologia.rest.mapper.PessoaMapper;
import com.dynns.cloudtecnologia.service.PessoaService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {


    @Inject
    private PessoaMapper pessoaMapper;

    @Inject
    private PessoaRepository pessoaRepository;

    @Inject
    @RestClient
    private ViaCepClient viaCepClient;


    @Override
    @Transactional
    public Pessoa salvar(PessoaDTONew pessoaDTONew) {
        Pessoa newPessoa = pessoaMapper.pessoaDTONewToPessoa(pessoaDTONew);
        pessoaRepository.persist(newPessoa);
        return newPessoa;
    }

    @Override
    public List<Pessoa> listar() {
        return pessoaRepository.findAll().list();
    }

    @Override
    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @Override
    public Pessoa buscarPorCpfOrThrow(String cpf) {
        return pessoaRepository.findByCpfOrThrow(cpf);
    }

    @Override
    public Pessoa buscarPorTituloEleitoral(String tituloEleitoral) {
        return pessoaRepository.findByTituloEleitoral(tituloEleitoral);
    }

    @Override
    public EnderecoDTO buscarEnderecoPessoaPorCpf(String cpf, String cep) {
        return viaCepClient.getEnderecoByCep(cep);
    }

    @Override
    public boolean cepIsValido(String cep) {
        EnderecoDTO enderecoDTO = viaCepClient.getEnderecoByCep(cep);
        return enderecoDTO.getCep() != null;
    }

    @Override
    @Transactional
    public Pessoa atualizar(PessoaDTOUpdate pessoaDTOUpdate) {
        Pessoa pessoa = pessoaRepository.findByIdOrThrow(pessoaDTOUpdate.getId());

        pessoa.setNome(pessoaDTOUpdate.getNome());
        pessoa.setCep(pessoaDTOUpdate.getCep());
        pessoa.setTelefone(pessoaDTOUpdate.getTelefone());
        pessoa.setEmail(pessoaDTOUpdate.getEmail());
        pessoa.setTituloEleitoral(pessoaDTOUpdate.getTituloEleitoral());
        pessoa.setNacionalidade(pessoaDTOUpdate.getNacionalidade());

        return pessoa;
    }

    @Override
    @Transactional
    public boolean deletar(Long id) {
        pessoaRepository.findByIdOrThrow(id);
        return pessoaRepository.deleteById(id);
    }

    @Override
    public List<PessoaReflectionDTO> getPessoasReflection() {
        return pessoaRepository.getPessoasReflection();
    }

}
