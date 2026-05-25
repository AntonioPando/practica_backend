package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
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
    public DireccionDTO crearDireccion(@RequestBody DireccionDTO direccionDTO) {
        DireccionEntity entity = new DireccionEntity();
        entity.setNombreCalle(direccionDTO.getNombreCalle());
        entity.setNumeroCalle(direccionDTO.getNumeroCalle());
        entity.setDireccionPrincipal(direccionDTO.getDireccionPrincipal());
        if (direccionDTO.getUsuarioId() != null) {
            UsuarioEntity usuarioRef = new UsuarioEntity();
            usuarioRef.setId(direccionDTO.getUsuarioId());
            entity.setUsuario(usuarioRef);
        } else if (direccionDTO.getUsuario() != null && direccionDTO.getUsuario().getId() != null) {
            UsuarioEntity usuarioRef = new UsuarioEntity();
            usuarioRef.setId(direccionDTO.getUsuario().getId());
            entity.setUsuario(usuarioRef);
        }
        DireccionEntity saved = direccionService.crearDireccion(entity);
        DireccionDTO result = new DireccionDTO();
        result.setId(saved.getId());
        result.setNombreCalle(saved.getNombreCalle());
        result.setNumeroCalle(saved.getNumeroCalle());
        result.setDireccionPrincipal(saved.getDireccionPrincipal());
        if (saved.getUsuario() != null) result.setUsuarioId(saved.getUsuario().getId());
        return result;
    }

    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Integer id) {
        DireccionEntity deleted = direccionService.eliminarDireccionYDevolver(id);
        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Direccion no encontrada");
        }
        DireccionDTO result = new DireccionDTO();
        result.setId(deleted.getId());
        result.setNombreCalle(deleted.getNombreCalle());
        result.setNumeroCalle(deleted.getNumeroCalle());
        result.setDireccionPrincipal(deleted.getDireccionPrincipal());
        if (deleted.getUsuario() != null) result.setUsuarioId(deleted.getUsuario().getId());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/direcciones/{id}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Integer id, @RequestBody DireccionDTO direccionDTO) {
        DireccionEntity datos = new DireccionEntity();
        datos.setNombreCalle(direccionDTO.getNombreCalle());
        datos.setNumeroCalle(direccionDTO.getNumeroCalle());
        datos.setDireccionPrincipal(direccionDTO.getDireccionPrincipal());
        if (direccionDTO.getUsuarioId() != null) {
            UsuarioEntity u = new UsuarioEntity();
            u.setId(direccionDTO.getUsuarioId());
            datos.setUsuario(u);
        } else if (direccionDTO.getUsuario() != null && direccionDTO.getUsuario().getId() != null) {
            UsuarioEntity u = new UsuarioEntity();
            u.setId(direccionDTO.getUsuario().getId());
            datos.setUsuario(u);
        }
        DireccionEntity updated = direccionService.actualizarDireccion(id, datos);
        if (updated == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Direccion no encontrada");
        DireccionDTO result = new DireccionDTO();
        result.setId(updated.getId());
        result.setNombreCalle(updated.getNombreCalle());
        result.setNumeroCalle(updated.getNumeroCalle());
        result.setDireccionPrincipal(updated.getDireccionPrincipal());
        if (updated.getUsuario() != null) result.setUsuarioId(updated.getUsuario().getId());
        return ResponseEntity.ok(result);
    }

}
