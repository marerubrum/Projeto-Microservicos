package br.com.cursomensageria.msclientes.infra.repository;

import br.com.cursomensageria.msclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
