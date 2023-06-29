package com.dynns.cloudtecnologia.anottation.impl;

import com.dynns.cloudtecnologia.anottation.TituloEleitoralUnico;
import com.dynns.cloudtecnologia.service.impl.PessoaServiceImpl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class TituloEleitoralUnicoImpl implements ConstraintValidator<TituloEleitoralUnico, String> {

    @Inject
    private PessoaServiceImpl pessoaService;

    @Override
    public boolean isValid(String tituloEleitoral, ConstraintValidatorContext constraintValidatorContext) {
        return pessoaService.buscarPorTituloEleitoral(tituloEleitoral).getId() == null;
    }
}
