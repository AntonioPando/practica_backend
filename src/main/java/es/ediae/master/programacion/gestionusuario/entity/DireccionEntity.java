package es.ediae.master.programacion.gestionusuario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "direccion")
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre_calle", nullable=false)
    private String nombreCalle;

    @Column(name="numero_calle", nullable=false)
    private Integer numeroCalle;

    @Column(name="direccion_principal", nullable=false)
    private Boolean direccionPrincipal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable=true)
    private UsuarioEntity usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public Integer getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(Integer numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public Boolean getDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(Boolean direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
