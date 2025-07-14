package Agendador.example.Agendador.validacao;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContatoValidoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContato {
    String message() default "CPF ou CNPJ obrigatório de acordo com o tipo de pessoa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
