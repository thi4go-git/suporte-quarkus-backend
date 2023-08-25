package com.dynns.cloudtecnologia.anottation;


import com.dynns.cloudtecnologia.anottation.impl.TituloEleitoralUnicoImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = TituloEleitoralUnicoImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TituloEleitoralUnico {
    String message() default "O TÍTULO ELEITORAL Informado já está sendo usado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
