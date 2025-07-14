package Agendador.example.Agendador.dto;

import Agendador.example.Agendador.entidades.Endereco;
import Agendador.example.Agendador.validacao.ValidCEP;
import jakarta.validation.constraints.NotBlank;

public class EnderecoDTO {

    @NotBlank(message = "CEP é obrigatório")
    @ValidCEP
    private String cep;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    private String complemento;

    public EnderecoDTO() {}

    public EnderecoDTO(Endereco endereco) {
        if (endereco != null) {
            this.cep = endereco.getCep();
            this.logradouro = endereco.getLogradouro();
            this.cidade = endereco.getCidade();
            this.estado = endereco.getEstado();
            this.numero = endereco.getNumero();
            this.complemento = endereco.getComplemento();
        }
    }

    public Endereco toEntity() {
        Endereco e = new Endereco();
        e.setCep(cep);
        e.setLogradouro(logradouro);
        e.setCidade(cidade);
        e.setEstado(estado);
        e.setNumero(numero);
        e.setComplemento(complemento);
        return e;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
