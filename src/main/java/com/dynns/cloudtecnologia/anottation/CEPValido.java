package com.dynns.cloudtecnologia.anottation;

import com.dynns.cloudtecnologia.anottation.impl.CEPValidoImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CEPValidoImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CEPValido {
    String message() default "CEP é inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
