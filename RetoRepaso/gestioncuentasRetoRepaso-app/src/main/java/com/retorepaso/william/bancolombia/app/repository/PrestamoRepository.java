package com.retorepaso.william.bancolombia.app.repository;

import com.retorepaso.william.bancolombia.app.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    // Obtener los últimos 3 préstamos de un cliente
    @Query("SELECT p FROM Prestamo p WHERE p.cliente.id = :clienteId ORDER BY p.fechaCreacion DESC")
    List<Prestamo> findTop3ByClienteId(Long cliente_id);
    List<Prestamo> findByClienteIdOrderByFechaCreacionDesc(Long cliente_id);
}
