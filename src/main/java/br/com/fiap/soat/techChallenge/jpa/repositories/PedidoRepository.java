package br.com.fiap.soat.techChallenge.jpa.repositories;

import br.com.fiap.soat.techChallenge.jpa.entities.PedidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoJpaEntity, UUID> {}
