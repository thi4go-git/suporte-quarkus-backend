package com.dynns.cloudtecnologia.rest.dto;

import com.dynns.cloudtecnologia.anottation.CEPValido;
import com.dynns.cloudtecnologia.anottation.CPFUnico;
import com.dynns.cloudtecnologia.anottation.TituloEleitoralUnico;
import com.dynns.cloudtecnologia.model.enums.NacionalidadeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.br.TituloEleitoral;

import jakarta.validation.constraints.*;


@Data
public class PessoaDTONew {

    @Size(min = 5, message = "O campo NOME deve conter 5 ou mais caracteres!")
    @NotBlank(message = "O campo nome é obrigatorio!")
    private String nome;

    @CPFUnico
    @CPF(message = "CPF Inválido!")
    @NotBlank(message = "O campo cpf é obrigatorio!")
    private String cpf;

    @CEPValido
    @Digits(integer = 10, fraction = 0, message = "O Campo CEP só aceita valores numéricos!")
    @NotBlank(message = "O campo cep é obrigatorio!")
    @Size(min = 8, max = 8, message = "O CEP deve possuir 8 caracteres!")
    private String cep;

    @NotBlank(message = "O campo telefone é obrigatorio!")
    @Size(min = 10, max = 11, message = "O TELEFONE deve possuir entre 10 e 11 Números!")
    @Digits(integer = 10, fraction = 0, message = "O Campo TELEFONE só aceita valores numéricos!")
    private String telefone;

    @NotBlank(message = "O campo email é obrigatorio!")
    @Email(message = "Email inválido")
    private String email;

    @TituloEleitoralUnico
    @TituloEleitoral(message = "Titulo Eleitoral Inválido!")
    @NotBlank(message = "O campo tituloEleitoral é obrigatorio!")
    private String tituloEleitoral;

    @NotNull(message = "O campo nacionalidade é obrigatorio!")
    private NacionalidadeEnum nacionalidade;

    private boolean ativo;

}
