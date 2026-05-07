package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/api/v1")
public class GeneroController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/generos")
    public List<GeneroEntity> obtenerTodos() {
        return em.createQuery("SELECT g FROM GeneroEntity g", GeneroEntity.class).getResultList();
    }

    @GetMapping("/generos/{id}")
    public GeneroEntity obtenerPorId(@PathVariable Integer id) {
        return em.find(GeneroEntity.class, id);
    }
}
