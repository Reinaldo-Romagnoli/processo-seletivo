package Agendador.example.Agendador.dto;

import Agendador.example.Agendador.entidades.Contato;
import Agendador.example.Agendador.entidades.TipoPessoa;

public class ContatoResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private EnderecoDTO endereco;
    private TipoPessoa tipoPessoa;

    public ContatoResponseDTO(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.cpf = contato.getCpf();
        this.email = contato.getEmail();
        this.telefone = contato.getTelefone();
        this.endereco = new EnderecoDTO(contato.getEndereco());
        this.tipoPessoa = contato.getTipoPessoa();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }
}
