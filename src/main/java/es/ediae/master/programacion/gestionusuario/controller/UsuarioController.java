package es.ediae.master.programacion.gestionusuario.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/api/v1/")
public class UsuarioController {

    // Note: project doesn't have a UsuarioService; using EntityManager here to persist.

    @PersistenceContext
    private EntityManager em;

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
    @Transactional
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioPostDTO usuarioPostDTO) {
        // Basic validation
        if (usuarioPostDTO.getNickUsuario() == null || usuarioPostDTO.getNickUsuario().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario es obligatorio");
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNickUsuario(usuarioPostDTO.getNickUsuario());
        // password is required in entity; set a default temporary password if not provided
        entity.setPassword("");
        entity.setNombre(usuarioPostDTO.getNombre());
        entity.setPrimerApellido(usuarioPostDTO.getPrimerApellido());
        entity.setSegundoApellido(usuarioPostDTO.getSegundoApellido());
        entity.setFechaNacimiento(usuarioPostDTO.getFechaNacimiento());
        entity.setFechaHoraCreacion(usuarioPostDTO.getFechaHoraCreacion() != null ? usuarioPostDTO.getFechaHoraCreacion() : LocalDateTime.now());
        entity.setHoraDesayuno(usuarioPostDTO.getHoraDesayuno());
        entity.setEsAdmin(usuarioPostDTO.getEsAdmin() != null ? usuarioPostDTO.getEsAdmin() : false);

        // Set relations if provided
        if (usuarioPostDTO.getGeneroId() != null) {
            GeneroEntity g = em.find(GeneroEntity.class, usuarioPostDTO.getGeneroId());
            entity.setGenero(g);
        }
        if (usuarioPostDTO.getPuestoDeTrabajoId() != null) {
            PuestoDeTrabajoEntity p = em.find(PuestoDeTrabajoEntity.class, usuarioPostDTO.getPuestoDeTrabajoId());
            entity.setPuestoDeTrabajo(p);
        }

        em.persist(entity);
        em.flush();

        UsuarioModel model = UsuarioModel.fromEntity(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginDTO loginDTO) {
        if (loginDTO.getNickUsuario() == null || loginDTO.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario y password son obligatorios");
        }

        List<UsuarioEntity> encontrados = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.nickUsuario = :nick", UsuarioEntity.class)
                .setParameter("nick", loginDTO.getNickUsuario())
                .getResultList();

        if (encontrados.isEmpty()) {
            return ResponseEntity.ok(false);
        }

        UsuarioEntity usuario = encontrados.get(0);
        boolean autenticado = usuario.getPassword() != null && usuario.getPassword().equals(loginDTO.getPassword());
        return ResponseEntity.ok(autenticado);
    }

    @DeleteMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        UsuarioEntity usuario = em.find(UsuarioEntity.class, id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        UsuarioModel modelo = UsuarioModel.fromEntity(usuario);
        em.remove(usuario);
        return ResponseEntity.ok(modelo);
    }

    @PutMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioPostDTO usuarioPostDTO) {
        UsuarioEntity usuario = em.find(UsuarioEntity.class, id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if (usuarioPostDTO.getNickUsuario() != null) {
            if (usuarioPostDTO.getNickUsuario().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario no puede estar vacío");
            }
            usuario.setNickUsuario(usuarioPostDTO.getNickUsuario());
        }
        if (usuarioPostDTO.getNombre() != null) usuario.setNombre(usuarioPostDTO.getNombre());
        if (usuarioPostDTO.getPrimerApellido() != null) usuario.setPrimerApellido(usuarioPostDTO.getPrimerApellido());
        if (usuarioPostDTO.getSegundoApellido() != null) usuario.setSegundoApellido(usuarioPostDTO.getSegundoApellido());
        if (usuarioPostDTO.getFechaNacimiento() != null) usuario.setFechaNacimiento(usuarioPostDTO.getFechaNacimiento());
        if (usuarioPostDTO.getFechaHoraCreacion() != null) usuario.setFechaHoraCreacion(usuarioPostDTO.getFechaHoraCreacion());
        if (usuarioPostDTO.getHoraDesayuno() != null) usuario.setHoraDesayuno(usuarioPostDTO.getHoraDesayuno());

        if (usuarioPostDTO.getEsAdmin() != null) {
            usuario.setEsAdmin(usuarioPostDTO.getEsAdmin());
        }

        if (usuarioPostDTO.getGeneroId() != null) {
            GeneroEntity g = em.find(GeneroEntity.class, usuarioPostDTO.getGeneroId());
            usuario.setGenero(g);
        }
        if (usuarioPostDTO.getPuestoDeTrabajoId() != null) {
            PuestoDeTrabajoEntity p = em.find(PuestoDeTrabajoEntity.class, usuarioPostDTO.getPuestoDeTrabajoId());
            usuario.setPuestoDeTrabajo(p);
        }

        UsuarioEntity updated = em.merge(usuario);
        em.flush();
        UsuarioModel model = UsuarioModel.fromEntity(updated);
        return ResponseEntity.ok(model);
    }

    

}
