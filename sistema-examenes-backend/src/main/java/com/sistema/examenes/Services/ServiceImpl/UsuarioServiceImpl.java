package com.sistema.examenes.Services.ServiceImpl;

import com.sistema.examenes.Entity.Usuario;
import com.sistema.examenes.Entity.UsuarioRol;
import com.sistema.examenes.Repository.RolRespository;
import com.sistema.examenes.Repository.UsuarioRepository;
import com.sistema.examenes.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRespository rolRespository;


    // Guardar  nuevo usuario en caso no exista aún....
    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioLocal != null) {
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya existe en db");
        } else {
            /* for (UsuarioRol usuarioRol : usuarioRoles) {
                rolRespository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario); */
            usuarioRoles.forEach(usuarioRol -> rolRespository.save(usuarioRol.getRol()));
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }

        return usuarioLocal;
    }

    // Busqueda por username
    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
    // Delete por id
    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

}
