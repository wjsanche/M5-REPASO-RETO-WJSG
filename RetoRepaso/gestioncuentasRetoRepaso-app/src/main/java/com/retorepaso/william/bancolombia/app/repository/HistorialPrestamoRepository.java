package com.retorepaso.william.bancolombia.app.repository;

import com.retorepaso.william.bancolombia.app.model.HistorialPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialPrestamoRepository extends JpaRepository<HistorialPrestamo, Long> {
    List<HistorialPrestamo> findByPrestamoId(Long prestamoId);
}
