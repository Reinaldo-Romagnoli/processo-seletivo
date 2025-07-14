package Agendador.example.Agendador.validacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNPJValidator implements ConstraintValidator<ValidCNPJ, String> {

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null || cnpj.isBlank()) {
            return true;
        }
        if (!cnpj.matches("\\d{14}")) return false;
        if (cnpj.chars().distinct().count() == 1) return false;

        try {
            int soma = 0, peso = 5;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
                peso = (peso == 2) ? 9 : peso - 1;
            }

            int digito1 = soma % 11;
            digito1 = (digito1 < 2) ? 0 : 11 - digito1;

            soma = 0;
            peso = 6;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
                peso = (peso == 2) ? 9 : peso - 1;
            }

            int digito2 = soma % 11;
            digito2 = (digito2 < 2) ? 0 : 11 - digito2;

            return Character.getNumericValue(cnpj.charAt(12)) == digito1 &&
                    Character.getNumericValue(cnpj.charAt(13)) == digito2;
        } catch (Exception e) {
            return false;
        }
    }
}
