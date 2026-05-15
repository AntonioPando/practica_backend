package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioModel;

@Service
public interface IUsuarioService {

    List<UsuarioModel> listarUsuarios();

    UsuarioModel obtenerUsuario(Integer id);

    public boolean iniciarSesion(String nickUsuario, String password);

}
