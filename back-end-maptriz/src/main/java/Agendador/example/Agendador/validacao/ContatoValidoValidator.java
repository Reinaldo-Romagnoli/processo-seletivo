package Agendador.example.Agendador.validacao;

import Agendador.example.Agendador.dto.ContatoRequestDTO;
import Agendador.example.Agendador.entidades.TipoPessoa;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContatoValidoValidator implements ConstraintValidator<ValidContato, ContatoRequestDTO> {

    @Override
    public boolean isValid(ContatoRequestDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getTipoPessoa() == null) return true;

        boolean valido = true;

        if (dto.getTipoPessoa() == TipoPessoa.FISICA) {
            valido = dto.getCpf() != null && !dto.getCpf().isBlank();
        } else if (dto.getTipoPessoa() == TipoPessoa.JURIDICA) {
            valido = dto.getCnpj() != null && !dto.getCnpj().isBlank();
        }

        if (!valido) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("CPF ou CNPJ obrigatório de acordo com o tipo de pessoa")
                    .addPropertyNode(dto.getTipoPessoa() == TipoPessoa.FISICA ? "cpf" : "cnpj")
                    .addConstraintViolation();
        }

        return valido;
    }
}
