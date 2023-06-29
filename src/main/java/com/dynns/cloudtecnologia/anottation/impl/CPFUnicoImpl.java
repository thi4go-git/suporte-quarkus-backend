package com.dynns.cloudtecnologia.anottation.impl;

import com.dynns.cloudtecnologia.anottation.CPFUnico;
import com.dynns.cloudtecnologia.service.impl.PessoaServiceImpl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CPFUnicoImpl implements ConstraintValidator<CPFUnico, String> {


    @Inject
    private PessoaServiceImpl pessoaService;


    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {


        return pessoaService.buscarPorCpf(cpf).getId() == null;




    }



}
