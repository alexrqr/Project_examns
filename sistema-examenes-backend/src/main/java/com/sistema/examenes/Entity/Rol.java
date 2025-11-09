package com.sistema.examenes.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    private Long rolId;

    private String nombre;
    private String rolNombre;

    // Relaciones de rol a usuarioRol
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    // GETTER AND SETTER
    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRolNombre() {
        return rolNombre;
    }
    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

    // Constructor
    public Rol() {
    }
    public Rol(Long rolId, String nombre, String rolNombre) {
        this.rolId = rolId;
        this.nombre = nombre;
        this.rolNombre = rolNombre;
    }
}
