package com.sistema.examenes;

import com.sistema.examenes.Entity.Rol;
import com.sistema.examenes.Entity.Usuario;
import com.sistema.examenes.Entity.UsuarioRol;
import com.sistema.examenes.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {

    // Inyección de usuarioService
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Metodo que se ejecuta al correr el proyecto....
    @Override
    public void run(String... args) throws Exception {
        /* Usuario usuario = new Usuario();
        usuario.setNombre("alex");
        usuario.setApellido("romero");
        usuario.setUsername("alex");
        // Password encriptado
        usuario.setPassword(this.bCryptPasswordEncoder.encode("12345678"));
        // usuario.setPassword("12345678");
        usuario.setEmail("alex@gmail.com");
        usuario.setTelefono("906141626");
        usuario.setPerfil("foto.png");

        Rol rol = new Rol();
        rol.setRolId(1L);
        rol.setNombre("ADMIN");
        rol.setRolNombre("ADMIN");

        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);
        usuarioRoles.add(usuarioRol);

        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
        System.out.println("Usuario guardado: **** " + usuarioGuardado.getUsername()+ " ****"); */
    }

    public static void main(String[] args) {
        SpringApplication.run(SistemaExamenesBackendApplication.class, args);
    }

}
