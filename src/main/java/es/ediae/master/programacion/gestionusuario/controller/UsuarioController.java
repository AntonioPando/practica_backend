package es.ediae.master.programacion.gestionusuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioModel;

@RestController
@RequestMapping("/api/v1/")
public class UsuarioController {

    // Note: project doesn't have a UsuarioService; using repository/service layer can be added later.

    @GetMapping("/usuarios")
    public ResponseEntity<String> listarUsuarios() {
        // Placeholder: return informative message until service is implemented
        return ResponseEntity.ok("Endpoint listarUsuarios - implementar servicio");
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<String> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok("Endpoint obtenerUsuario id=" + id + " - implementar servicio");
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioPostDTO usuarioPostDTO) {
        // Basic validation
        if (usuarioPostDTO.getNickUsuario() == null || usuarioPostDTO.getNickUsuario().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario es obligatorio");
        }
        // Map to model (placeholder)
        UsuarioModel model = new UsuarioModel();
        model.setNickUsuario(usuarioPostDTO.getNickUsuario());
        model.setNombre(usuarioPostDTO.getNombre());
        model.setPrimerApellido(usuarioPostDTO.getPrimerApellido());
        model.setSegundoApellido(usuarioPostDTO.getSegundoApellido());
        model.setFechaNacimiento(usuarioPostDTO.getFechaNacimiento());
        model.setFechaHoraCreacion(usuarioPostDTO.getFechaHoraCreacion());
        model.setHoraDesayuno(usuarioPostDTO.getHoraDesayuno());
        model.setGeneroId(usuarioPostDTO.getGeneroId());
        model.setGeneroDescripcion(usuarioPostDTO.getGeneroDescripcion());
        model.setPuestoDeTrabajoId(usuarioPostDTO.getPuestoDeTrabajoId());

        // No persistence yet
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

}
