package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionService;

@RestController
@RequestMapping("/api/v1/")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping("/usuario/{usuarioId}/direcciones")
    public List<DireccionEntity> obtenerDireccionesPorUsuarioId(@PathVariable Integer usuarioId) {
        return direccionService.buscarPorUsuarioId(usuarioId);
    }

    @GetMapping("/direcciones")
    public List<DireccionEntity> obtenerTodasLasDirecciones() {
        return direccionService.obtenerAllDirecciones();
    }

    @GetMapping("/direcciones/{id}")
    public List<DireccionEntity> obtenerDireccionPorId(@PathVariable Integer id) {
        return direccionService.buscarPorUsuarioId(id);
    }

    @PostMapping("/direcciones")
    public DireccionEntity crearDireccion(DireccionEntity direccion) {
        return direccionService.crearDireccion(direccion);
    }
}
