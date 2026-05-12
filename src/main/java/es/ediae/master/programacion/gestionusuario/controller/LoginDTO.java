package es.ediae.master.programacion.gestionusuario.controller;

public class LoginDTO {

    private String nickUsuario;
    private String password;

    public LoginDTO() {}

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

}
