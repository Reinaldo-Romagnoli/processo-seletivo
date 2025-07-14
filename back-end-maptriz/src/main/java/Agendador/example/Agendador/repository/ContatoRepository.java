package Agendador.example.Agendador.repository;

import Agendador.example.Agendador.entidades.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Page<Contato> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
