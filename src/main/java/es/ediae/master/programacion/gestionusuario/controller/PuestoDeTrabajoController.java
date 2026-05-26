package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class PuestoDeTrabajoController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/puestos")
    public List<PuestoDeTrabajoEntity> obtenerTodos() {
        return em.createQuery("SELECT p FROM PuestoDeTrabajoEntity p", PuestoDeTrabajoEntity.class)
                .getResultList();
    }

    @GetMapping("/puestos/{id}")
    public PuestoDeTrabajoEntity obtenerPorId(@PathVariable Integer id) {
        return em.find(PuestoDeTrabajoEntity.class, id);
    }
}
