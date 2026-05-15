package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.repository.UsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioModel::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UsuarioModel obtenerUsuario(Integer id) {
        return usuarioRepository.findById(id).map(UsuarioModel::fromEntity).orElse(null);
    }

    @Override
    public boolean iniciarSesion(String nickUsuario, String password) {
        return usuarioRepository.findByNickUsuarioAndPassword(nickUsuario, password).isPresent();
    }

}
