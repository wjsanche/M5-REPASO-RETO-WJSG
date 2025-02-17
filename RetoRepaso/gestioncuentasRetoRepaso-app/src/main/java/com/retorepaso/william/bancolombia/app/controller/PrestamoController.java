package com.retorepaso.william.bancolombia.app.controller;

import com.retorepaso.william.bancolombia.app.model.Prestamo;
import com.retorepaso.william.bancolombia.app.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    //  Inyección de dependencia mediante constructor
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    //  Obtener todos los préstamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> obtenerPrestamos() {
        List<Prestamo> prestamos = prestamoService.obtenerTodosLosPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    //  Obtener préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.obtenerPrestamoPorId(id);
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Crear un nuevo préstamo
    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamo);
        return ResponseEntity.ok(nuevoPrestamo);
    }


    // Endpoint para actualizar el estado de un préstamo
    @PutMapping("/{id}/estado")
    public ResponseEntity<Prestamo> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        Prestamo prestamoActualizado = prestamoService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(prestamoActualizado);
    }
    // Consultar los últimos 3 préstamos de un cliente
    @GetMapping("/cliente/{clienteId}/ultimos3")
    public ResponseEntity<List<Prestamo>> obtenerUltimos3Prestamos(@PathVariable Long clienteId) {
        List<Prestamo> prestamos = prestamoService.obtenerUltimos3Prestamos(clienteId);
        return ResponseEntity.ok(prestamos);
    }

    // Endpoint para obtener el historial de préstamos de un cliente
    @GetMapping("/cliente/{clienteId}/historial")
    public ResponseEntity<List<Prestamo>> obtenerHistorialPrestamos(@PathVariable Long clienteId) {
        List<Prestamo> historial = prestamoService.obtenerHistorialDePrestamos(clienteId);
        return ResponseEntity.ok(historial);
    }

}