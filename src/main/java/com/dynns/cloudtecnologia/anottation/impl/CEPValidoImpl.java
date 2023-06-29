package com.dynns.cloudtecnologia.anottation.impl;

import com.dynns.cloudtecnologia.anottation.CEPValido;
import com.dynns.cloudtecnologia.service.impl.PessoaServiceImpl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CEPValidoImpl implements ConstraintValidator<CEPValido, String> {

    @Inject
    private PessoaServiceImpl pessoaService;

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext constraintValidatorContext) {
        return pessoaService.cepIsValido(cep);
    }
}
