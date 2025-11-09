package com.sistema.examenes.Entity;

import jakarta.persistence.*;

@Entity
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    // Relaciones de usuarios-rol
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    // Relaciones de rol-usuarios
    @ManyToOne
    private Rol rol;


    // GETTER AND SETTER
    public Long getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(Long usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Constructor...
    public UsuarioRol() {
    }
}
