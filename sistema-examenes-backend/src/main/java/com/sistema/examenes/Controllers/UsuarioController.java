package com.sistema.examenes.Controllers;

import com.sistema.examenes.Entity.Rol;
import com.sistema.examenes.Entity.Usuario;
import com.sistema.examenes.Entity.UsuarioRol;
import com.sistema.examenes.Payload.ApiResponse;
import com.sistema.examenes.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Endpoint para el guardado de ususario..
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Usuario>> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setPerfil("default.png");

            // Password encriptado
            usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

            // Crear el rol NORMAL por defecto
            Set<UsuarioRol> usuarioRoles = new HashSet<>();
            Rol rol = new Rol();
            rol.setRolId(2L);
            rol.setNombre("NORMAL");
            rol.setRolNombre("NORMAL");

            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setRol(rol);
            usuarioRol.setUsuario(usuario);
            usuarioRoles.add(usuarioRol);

            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario, usuarioRoles);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Usuario registrado correctamente", nuevoUsuario, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo registrar el usuario", null, e.getMessage())
            );
        }
    }
    /* public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        // UsuarioRol
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        // Nuevo Rol para registro de usuario.....
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);

        // Guardado de usuarioRol....
        usuarioRoles.add(usuarioRol);

        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    } */

    // Busqueda por username
    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerUsuario(@PathVariable("username") String username) {
        try {
            Usuario usuario = usuarioService.obtenerUsuario(username);

            if (usuario == null) {
                return ResponseEntity.status(404).body(
                        new ApiResponse<>("error", "Usuario no encontrado", null, null)
                );
            }

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Usuario encontrado", usuario, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "Error al obtener el usuario", null, e.getMessage())
            );
        }
    }
    /* public Usuario obtenerUsuario(@PathVariable("username") String username) throws Exception{
        return usuarioService.obtenerUsuario(username);
    } */

    // Delete de un usuario
    @DeleteMapping("/delete/{usuarioId}")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        try {
            usuarioService.eliminarUsuario(usuarioId);
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Usuario eliminado correctamente", null, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "Error al eliminar el usuario", null, e.getMessage())
            );
        }
    }
    /* public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) throws Exception{
        usuarioService.eliminarUsuario(usuarioId);
    } */

}
