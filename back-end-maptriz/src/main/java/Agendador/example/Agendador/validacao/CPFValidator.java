package Agendador.example.Agendador.validacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        // CPF vazio ou nulo pode ser válido (validação condicional no DTO)
        if (cpf == null || cpf.isBlank()) {
            return true;
        }

        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (!cpf.matches("\\d{11}")) return false;

        // Rejeita CPFs com todos os dígitos iguais
        if (cpf.chars().distinct().count() == 1) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int digito1 = 11 - (soma % 11);
            if (digito1 >= 10) digito1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int digito2 = 11 - (soma % 11);
            if (digito2 >= 10) digito2 = 0;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                    digito2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}