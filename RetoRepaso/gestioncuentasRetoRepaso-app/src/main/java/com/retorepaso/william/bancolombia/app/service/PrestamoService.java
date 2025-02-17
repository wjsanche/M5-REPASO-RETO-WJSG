package com.retorepaso.william.bancolombia.app.service;

import com.retorepaso.william.bancolombia.app.model.EstadoPrestamo;
import com.retorepaso.william.bancolombia.app.model.HistorialPrestamo;
import com.retorepaso.william.bancolombia.app.model.Prestamo;
import com.retorepaso.william.bancolombia.app.repository.HistorialPrestamoRepository;
import com.retorepaso.william.bancolombia.app.repository.PrestamoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HistorialPrestamoRepository historialPrestamoRepository;
    //  Inyección de dependencia mediante constructor
    public PrestamoService(PrestamoRepository prestamoRepository, HistorialPrestamoRepository historialPrestamoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.historialPrestamoRepository = historialPrestamoRepository;
    }

    //  Obtener todos los préstamos
    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    //  Obtener préstamo por ID
    public Optional<Prestamo> obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    //  Guardar nuevo préstamo
    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    // Actualizar estado del préstamo
    public Prestamo actualizarEstado(Long id, String nuevoEstado) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);

        if (prestamoOpt.isPresent()) {
            Prestamo prestamo = prestamoOpt.get();
            String estadoAnterior = prestamo.getEstado(); // Guardamos el estado anterior

            prestamo.setEstado(nuevoEstado);
            prestamo.setFechaActualizacion(java.time.LocalDate.now());
            Prestamo prestamoActualizado = prestamoRepository.save(prestamo);

            // Registrar el cambio en el historial
            HistorialPrestamo historial = new HistorialPrestamo();
            historial.setPrestamo(prestamo);
            historial.setMonto(prestamo.getMonto());
            historial.setEstadoAnterior(estadoAnterior);
            historial.setEstadoNuevo(nuevoEstado);
            historial.setFechaCambio(java.time.LocalDateTime.now());

            historialPrestamoRepository.save(historial);

            return prestamoActualizado;
        } else {
            throw new RuntimeException("Préstamo no encontrado");
        }
    }
    //Obtener los ultimos 3 prestamos por cliente
    public List<Prestamo> obtenerUltimos3Prestamos(Long clienteId) {
        return prestamoRepository.findTop3ByClienteId(clienteId);
    }
    //  Obtener el historial de prestamos por cliente
    public List<Prestamo> obtenerHistorialDePrestamos(Long clienteId) {
        return prestamoRepository.findByClienteIdOrderByFechaCreacionDesc(clienteId);
    }


}