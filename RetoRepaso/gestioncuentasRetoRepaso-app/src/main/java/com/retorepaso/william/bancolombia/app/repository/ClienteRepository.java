package com.retorepaso.william.bancolombia.app.repository;

import com.retorepaso.william.bancolombia.app.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
