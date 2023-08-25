package com.dynns.cloudtecnologia.rest.mapper;

import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.rest.dto.*;


import org.modelmapper.ModelMapper;


import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PessoaMapper {

    /**
     * Converte o objeto  PessoaDTONew para um novo objeto Pessoa que será salvo
     *
     * @param dtoNew
     * @return
     */
    public Pessoa pessoaDTONewToPessoa(final PessoaDTONew dtoNew) {

        ModelMapper modelMapper = new ModelMapper();
        Pessoa newPessoa = modelMapper.map(dtoNew, Pessoa.class);

        newPessoa.setDataCadastro(LocalDate.now());
        newPessoa.setAtivo(true);

        return newPessoa;

    }

    /**
     * Converte um objeto Pessoa para PessoaDTOView
     *
     * @param pessoa
     * @return
     */
    public PessoaDTOView pessoaToPessoaDTOView(final Pessoa pessoa) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pessoa, PessoaDTOView.class);
    }


    /**
     * Converte um objeto List<Pessoa> para List<PessoaDTOView>
     *
     * @param listPessoas
     * @return
     */
    public List<PessoaDTOView> listPessoaToListPessoaDTOView(final List<Pessoa> listPessoas) {
        return listPessoas.stream().map(this::pessoaToPessoaDTOView).collect(Collectors.toList());
    }


    /**
     * Recebe 2 objetos: (Pessoa e EnderecoDTO) e retorna um novo dto: PessoaEnderecoDTO
     *
     * @param pessoa
     * @param enderecoDTO
     * @return
     */
    public PessoaEnderecoDTO pessoaDTOViewAndEnderetoDtoToPessoaEnderecoDTO(Pessoa pessoa, EnderecoDTO enderecoDTO) {
        PessoaEnderecoDTO pessoaEnderecoDTO = new PessoaEnderecoDTO();
        pessoaEnderecoDTO.setPessoaDTOView(this.pessoaToPessoaDTOView(pessoa));
        pessoaEnderecoDTO.setEnderecoDTO(enderecoDTO);

        return pessoaEnderecoDTO;
    }

    public PessoaDTOUpdate pessoaDTONewToPessoaDTOUpdate(PessoaDTONew pessoaDTONew){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pessoaDTONew, PessoaDTOUpdate.class);
    }
}
