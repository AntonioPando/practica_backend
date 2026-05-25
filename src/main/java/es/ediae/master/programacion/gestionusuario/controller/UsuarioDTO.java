package es.ediae.master.programacion.gestionusuario.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class UsuarioDTO {

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
    
    // Relaciones: incluir objetos completos además de los IDs (para compatibilidad)
    private Integer generoId;
    private Integer puestoDeTrabajoId;
    private GeneroDTO genero;
    private PuestoDeTrabajoDTO puestoDeTrabajo;

    // Constructores
    public UsuarioDTO() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNickUsuario() { return nickUsuario; }
    public void setNickUsuario(String nickUsuario) { this.nickUsuario = nickUsuario; }

    public String getPassword() { return password; }
    public void setPassword(String contraseña) { this.password = contraseña; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public LocalDateTime getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDateTime fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDateTime getFechaHoraCreacion() { return fechaHoraCreacion; }
    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) { this.fechaHoraCreacion = fechaHoraCreacion; }

    public LocalTime getHoraDesayuno() { return horaDesayuno; }
    public void setHoraDesayuno(LocalTime horaDesayuno) { this.horaDesayuno = horaDesayuno; }

    public Integer getGeneroId() { return generoId; }
    public void setGeneroId(Integer generoId) { this.generoId = generoId; }

    public Integer getPuestoDeTrabajoId() { return puestoDeTrabajoId; }
    public void setPuestoDeTrabajoId(Integer puestoDeTrabajoId) { this.puestoDeTrabajoId = puestoDeTrabajoId; }

    public GeneroDTO getGenero() { return genero; }
    public void setGenero(GeneroDTO genero) { this.genero = genero; }

    public PuestoDeTrabajoDTO getPuestoDeTrabajo() { return puestoDeTrabajo; }
    public void setPuestoDeTrabajo(PuestoDeTrabajoDTO puestoDeTrabajo) { this.puestoDeTrabajo = puestoDeTrabajo; }
    public Boolean getEsAdmin() { return esAdmin; }
    public void setEsAdmin(Boolean esAdmin) { this.esAdmin = esAdmin; }
}