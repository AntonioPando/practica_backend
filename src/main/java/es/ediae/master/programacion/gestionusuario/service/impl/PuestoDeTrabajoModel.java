package es.ediae.master.programacion.gestionusuario.service.impl;

import es.ediae.master.programacion.gestionusuario.controller.PuestoDeTrabajoDTO;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;

public class PuestoDeTrabajoModel {
    private Integer id;
    private String nombre;

    public PuestoDeTrabajoModel() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public static PuestoDeTrabajoModel fromEntity(PuestoDeTrabajoEntity entity) {
        if (entity == null) return null;
        PuestoDeTrabajoModel model = new PuestoDeTrabajoModel();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        return model;
    }

    public static PuestoDeTrabajoModel fromDTO(PuestoDeTrabajoDTO dto) {
        if (dto == null) return null;
        PuestoDeTrabajoModel model = new PuestoDeTrabajoModel();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        return model;
    }
}
