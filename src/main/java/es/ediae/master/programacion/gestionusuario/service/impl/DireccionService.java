package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
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

    public void eliminarDireccion(Integer id) {
        this.direccionRepository.deleteById(id);
    }

    public DireccionEntity eliminarDireccionYDevolver(Integer id) {
        return this.direccionRepository.findById(id)
                .map(entity -> {
                    this.direccionRepository.delete(entity);
                    return entity;
                }).orElse(null);
    }

    public DireccionEntity actualizarDireccion(Integer id, DireccionEntity datos) {
        return this.direccionRepository.findById(id).map(entity -> {
            if (datos.getNombreCalle() != null) entity.setNombreCalle(datos.getNombreCalle());
            if (datos.getNumeroCalle() != null) entity.setNumeroCalle(datos.getNumeroCalle());
            if (datos.getDireccionPrincipal() != null) entity.setDireccionPrincipal(datos.getDireccionPrincipal());
            if (datos.getUsuario() != null && datos.getUsuario().getId() != null) {
                UsuarioEntity u = new UsuarioEntity();
                u.setId(datos.getUsuario().getId());
                entity.setUsuario(u);
            }
            return this.direccionRepository.save(entity);
        }).orElse(null);
    }

}
