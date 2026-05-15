package es.ediae.master.programacion.gestionusuario.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioModel;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET,
        RequestMethod.POST, 
        RequestMethod.PUT,
        RequestMethod.DELETE, 
        RequestMethod.OPTIONS })
@RequestMapping("/api/v1/")
public class UsuarioController {

    // Note: project doesn't have a UsuarioService; using EntityManager here to persist.

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        java.util.List<UsuarioModel> lista = usuarioService.listarUsuarios();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Integer id) {
        UsuarioModel model = usuarioService.obtenerUsuario(id);
        if (model == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        return ResponseEntity.ok(model);
    }

    @PostMapping("/usuarios")
    @Transactional
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioPostDTO usuarioPostDTO) {
        // Basic validation
        if (usuarioPostDTO.getNickUsuario() == null || usuarioPostDTO.getNickUsuario().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario es obligatorio");
        }

        // Unicidad de nickUsuario: no permitir crear si ya existe
        Long existentes = em.createQuery("SELECT COUNT(u) FROM UsuarioEntity u WHERE u.nickUsuario = :nick", Long.class)
                .setParameter("nick", usuarioPostDTO.getNickUsuario())
                .getSingleResult();
        if (existentes != null && existentes > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario ya existe");
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
    public boolean iniciarSesion(@RequestParam String nickUsuario, @RequestParam String password) {
        return usuarioService.iniciarSesion(nickUsuario, password);
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
            // Si cambia el nick, comprobar unicidad
            String nuevoNick = usuarioPostDTO.getNickUsuario();
            if (!nuevoNick.equals(usuario.getNickUsuario())) {
                Long existentes = em.createQuery("SELECT COUNT(u) FROM UsuarioEntity u WHERE u.nickUsuario = :nick AND u.id <> :id", Long.class)
                        .setParameter("nick", nuevoNick)
                        .setParameter("id", id)
                        .getSingleResult();
                if (existentes != null && existentes > 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nickUsuario ya existe");
                }
            }
            usuario.setNickUsuario(nuevoNick);
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
