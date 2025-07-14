package Agendador.example.Agendador.dto;

import Agendador.example.Agendador.entidades.TipoPessoa;
import Agendador.example.Agendador.validacao.ValidCNPJ;
import Agendador.example.Agendador.validacao.ValidCPF;
import Agendador.example.Agendador.validacao.ValidContato;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidContato
public class ContatoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @ValidCPF
    private String cpf;

    @ValidCNPJ
    private String cnpj;

    @Email(message = "E-mail inválido")
    private String email;

    private String telefone;

    @Valid
    private EnderecoDTO endereco;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    private TipoPessoa tipoPessoa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

}
