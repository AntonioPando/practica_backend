package es.ediae.master.programacion.gestionusuario.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;

import es.ediae.master.programacion.gestionusuario.controller.GeneroDTO;
import es.ediae.master.programacion.gestionusuario.controller.PuestoDeTrabajoDTO;
import es.ediae.master.programacion.gestionusuario.controller.UsuarioDTO;
import es.ediae.master.programacion.gestionusuario.controller.UsuarioPostDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

public class UsuarioModel {

    private Integer id;
    private String nickUsuario;
    private String password;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDateTime fechaNacimiento;
    private LocalDateTime fechaHoraCreacion;
    private LocalTime horaDesayuno;
    private Boolean esAdmin;

    // Simplificamos las relaciones enviando solo los IDs o nombres
    private Integer generoId;
    private String generoDescripcion;
    private Integer puestoDeTrabajoId;
    private GeneroDTO genero;
    private PuestoDeTrabajoDTO puestoDeTrabajo;

    // Constructores
    public UsuarioModel() {
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public LocalTime getHoraDesayuno() {
        return horaDesayuno;
    }

    public void setHoraDesayuno(LocalTime horaDesayuno) {
        this.horaDesayuno = horaDesayuno;
    }

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    public String getGeneroDescripcion() {
        return generoDescripcion;
    }

    public void setGeneroDescripcion(String generoDescripcion) {
        this.generoDescripcion = generoDescripcion;
    }

    public Integer getPuestoDeTrabajoId() {
        return puestoDeTrabajoId;
    }

    public void setPuestoDeTrabajoId(Integer puestoDeTrabajoId) {
        this.puestoDeTrabajoId = puestoDeTrabajoId;
    }

    public GeneroDTO getGenero() { return genero; }
    public void setGenero(GeneroDTO genero) { this.genero = genero; }

    public PuestoDeTrabajoDTO getPuestoDeTrabajo() { return puestoDeTrabajo; }
    public void setPuestoDeTrabajo(PuestoDeTrabajoDTO puestoDeTrabajo) { this.puestoDeTrabajo = puestoDeTrabajo; }
    public Boolean getEsAdmin() { return esAdmin; }
    public void setEsAdmin(Boolean esAdmin) { this.esAdmin = esAdmin; }

    public static UsuarioModel fromEntity(UsuarioEntity usuarioEntity) {
        UsuarioModel model = new UsuarioModel();
        model.setId(usuarioEntity.getId());
        model.setNickUsuario(usuarioEntity.getNickUsuario());
        model.setNombre(usuarioEntity.getNombre());
        model.setPrimerApellido(usuarioEntity.getPrimerApellido());
        model.setSegundoApellido(usuarioEntity.getSegundoApellido());
        model.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
        model.setFechaHoraCreacion(usuarioEntity.getFechaHoraCreacion());
        model.setHoraDesayuno(usuarioEntity.getHoraDesayuno());
        model.setEsAdmin(usuarioEntity.isEsAdmin());
        model.setGeneroId(usuarioEntity.getGenero() != null ? usuarioEntity.getGenero().getId() : null);
        if (usuarioEntity.getGenero() != null) {
            GeneroDTO g = new GeneroDTO();
            g.setId(usuarioEntity.getGenero().getId());
            g.setNombre(usuarioEntity.getGenero().getNombre());
            model.setGenero(g);
            model.setGeneroDescripcion(usuarioEntity.getGenero().getNombre());
        }
        model.setPuestoDeTrabajoId(usuarioEntity.getPuestoDeTrabajo() != null ? usuarioEntity.getPuestoDeTrabajo().getId() : null);
        if (usuarioEntity.getPuestoDeTrabajo() != null) {
            PuestoDeTrabajoDTO p = new PuestoDeTrabajoDTO();
            p.setId(usuarioEntity.getPuestoDeTrabajo().getId());
            p.setNombre(usuarioEntity.getPuestoDeTrabajo().getNombre());
            model.setPuestoDeTrabajo(p);
        }
        return model;
    }

    public static UsuarioModel fromDTO(UsuarioDTO usuarioDTO) {
        UsuarioModel model = new UsuarioModel();
        model.setId(usuarioDTO.getId());
        model.setNickUsuario(usuarioDTO.getNickUsuario());
        model.setNombre(usuarioDTO.getNombre());
        model.setPrimerApellido(usuarioDTO.getPrimerApellido());
        model.setSegundoApellido(usuarioDTO.getSegundoApellido());
        model.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        model.setFechaHoraCreacion(usuarioDTO.getFechaHoraCreacion());
        model.setHoraDesayuno(usuarioDTO.getHoraDesayuno());
        model.setEsAdmin(usuarioDTO.getEsAdmin());
        model.setGeneroId(usuarioDTO.getGeneroId());
        if (usuarioDTO.getGenero() != null) {
            model.setGenero(usuarioDTO.getGenero());
            if (usuarioDTO.getGenero().getId() != null) model.setGeneroId(usuarioDTO.getGenero().getId());
        }
        model.setPuestoDeTrabajoId(usuarioDTO.getPuestoDeTrabajoId());
        if (usuarioDTO.getPuestoDeTrabajo() != null) {
            model.setPuestoDeTrabajo(usuarioDTO.getPuestoDeTrabajo());
            if (usuarioDTO.getPuestoDeTrabajo().getId() != null) model.setPuestoDeTrabajoId(usuarioDTO.getPuestoDeTrabajo().getId());
        }
        return model;
    }

    public static UsuarioModel fromPostDTO(UsuarioPostDTO usuarioPostDTO) {
        UsuarioModel model = new UsuarioModel();
        model.setNickUsuario(usuarioPostDTO.getNickUsuario());
        model.setNombre(usuarioPostDTO.getNombre());
        model.setPrimerApellido(usuarioPostDTO.getPrimerApellido());
        model.setSegundoApellido(usuarioPostDTO.getSegundoApellido());
        model.setFechaNacimiento(usuarioPostDTO.getFechaNacimiento());
        model.setFechaHoraCreacion(usuarioPostDTO.getFechaHoraCreacion());
        model.setHoraDesayuno(usuarioPostDTO.getHoraDesayuno());
        model.setEsAdmin(usuarioPostDTO.getEsAdmin());
        model.setGeneroId(usuarioPostDTO.getGeneroId());
        model.setGeneroDescripcion(usuarioPostDTO.getGeneroDescripcion());
        model.setPuestoDeTrabajoId(usuarioPostDTO.getPuestoDeTrabajoId());
        return model;
    }

}
