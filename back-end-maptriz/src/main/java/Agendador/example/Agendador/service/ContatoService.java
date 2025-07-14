package Agendador.example.Agendador.service;

import Agendador.example.Agendador.dto.ContatoRequestDTO;
import Agendador.example.Agendador.dto.ContatoResponseDTO;
import Agendador.example.Agendador.entidades.Contato;
import Agendador.example.Agendador.exception.ResourceNotFoundException;
import Agendador.example.Agendador.repository.ContatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    private final ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public ContatoResponseDTO criar(ContatoRequestDTO dto) {
        return new ContatoResponseDTO(repository.save(dtoToEntity(dto)));
    }

    public ContatoResponseDTO editar(Long id, ContatoRequestDTO dto) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato com ID " + id + " não encontrado"));

        contato.setNome(dto.getNome());
        contato.setCpf(dto.getCpf());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());
        contato.setTipoPessoa(dto.getTipoPessoa());
        contato.setEndereco(dto.getEndereco().toEntity());

        return new ContatoResponseDTO(repository.save(contato));
    }

    public Page<ContatoResponseDTO> listar(String nome, Pageable pageable) {
        Page<Contato> pagina = (nome == null || nome.isBlank())
                ? repository.findAll(pageable)
                : repository.findByNomeContainingIgnoreCase(nome, pageable);

        return pagina.map(ContatoResponseDTO::new);
    }

    public Optional<ContatoResponseDTO> buscarPorId(Long id) {
        return repository.findById(id).map(ContatoResponseDTO::new);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private Contato dtoToEntity(ContatoRequestDTO dto) {
        Contato contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setCpf(dto.getCpf());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());
        contato.setTipoPessoa(dto.getTipoPessoa());
        contato.setEndereco(dto.getEndereco().toEntity());
        return contato;
    }
}

