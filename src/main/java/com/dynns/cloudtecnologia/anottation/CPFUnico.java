package com.dynns.cloudtecnologia.anottation;


import com.dynns.cloudtecnologia.anottation.impl.CPFUnicoImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CPFUnicoImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFUnico {
    String message() default "O CPF Informado já está sendo usado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
