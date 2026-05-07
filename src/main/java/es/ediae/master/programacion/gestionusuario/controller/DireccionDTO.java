package es.ediae.master.programacion.gestionusuario.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DireccionDTO {

    private Integer id;

    @JsonProperty("nombre_calle")
    private String nombreCalle;

    @JsonProperty("numero_calle")
    private Integer numeroCalle;

    @JsonProperty("direccion_principal")
    private Boolean direccionPrincipal;

    // Relación simplificada: solo el id del usuario o el DTO completo
    @JsonProperty("usuario_id")
    private Integer usuarioId;
    private UsuarioDTO usuario;

    public DireccionDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombreCalle() { return nombreCalle; }
    public void setNombreCalle(String nombreCalle) { this.nombreCalle = nombreCalle; }

    public Integer getNumeroCalle() { return numeroCalle; }
    public void setNumeroCalle(Integer numeroCalle) { this.numeroCalle = numeroCalle; }

    public Boolean getDireccionPrincipal() { return direccionPrincipal; }
    public void setDireccionPrincipal(Boolean direccionPrincipal) { this.direccionPrincipal = direccionPrincipal; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }

}
