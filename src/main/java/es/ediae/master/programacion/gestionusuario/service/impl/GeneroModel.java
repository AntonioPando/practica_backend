package es.ediae.master.programacion.gestionusuario.service.impl;

import es.ediae.master.programacion.gestionusuario.controller.GeneroDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;

public class GeneroModel {
    private Integer id;
    private String nombre;

    public GeneroModel() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public static GeneroModel fromEntity(GeneroEntity entity) {
        if (entity == null) return null;
        GeneroModel model = new GeneroModel();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        return model;
    }

    public static GeneroModel fromDTO(GeneroDTO GeneroDTO) {
        if (GeneroDTO == null) return null;
        GeneroModel model = new GeneroModel();
        model.setId(GeneroDTO.getId());
        model.setNombre(GeneroDTO.getNombre());
        return model;
    }


}
