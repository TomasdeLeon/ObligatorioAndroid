package com.example.obligatorioandroid;

import androidx.annotation.NonNull;

public class Usuario {

    private int usuarioId = 0;
    private String usuarioCorreo = "";
    private String usuarioContrasenia = "";

    // Nombre de la tabla
    public static final String TABLA = "Usuarios";

    // Campos de la tabla
    public static final String Usuario_Id = "Id";
    public static final String Usuario_Correo = "Correo";
    public static final String Usuario_Contrasenia = "Contrasenia";

    public Usuario(){
    }

    public Usuario(int usuarioId, String usuarioCorreo, String usuarioContrasenia){
        this.usuarioId = usuarioId;
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioContrasenia = usuarioContrasenia;

    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioContrasenia(String usuarioContrasenia) {
        this.usuarioContrasenia = usuarioContrasenia;
    }

    public String getUsuarioContrasenia() {
        return usuarioContrasenia;
    }
}
