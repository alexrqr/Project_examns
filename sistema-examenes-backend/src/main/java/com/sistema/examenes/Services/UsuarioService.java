package com.sistema.examenes.Services;

import com.sistema.examenes.Entity.Usuario;
import com.sistema.examenes.Entity.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    // Obtener el usuario:
    public Usuario obtenerUsuario(String username);
    // public void
    public void eliminarUsuario(Long usuarioId);

}
