package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.repository.DireccionRepository;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@Service
public class DireccionService implements IDireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public List<DireccionEntity> obtenerAllDirecciones() {
        return direccionRepository.findAll();
    }

    public List<DireccionEntity> buscarPorUsuarioId(Integer usuarioId) {
        return direccionRepository.buscarPorUsuarioId(usuarioId);
    }

    public DireccionEntity crearDireccion(DireccionEntity direccion) {
        return direccionRepository.save(direccion);
    }

}
