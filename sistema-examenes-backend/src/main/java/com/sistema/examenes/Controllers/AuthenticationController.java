package com.sistema.examenes.Controllers;

import com.sistema.examenes.Config.JwtUtils;
import com.sistema.examenes.Entity.JwtRequest;
import com.sistema.examenes.Entity.JwtResponse;
import com.sistema.examenes.Entity.Usuario;
import com.sistema.examenes.Entity.UsuarioRol;
import com.sistema.examenes.Exceptions.UsuarioNotFoundException;
import com.sistema.examenes.Services.ServiceImpl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 🔐 Genera un token JWT al iniciar sesión
     */
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest, HttpServletRequest request) throws Exception {
        System.out.println("📩 Login request recibido: " + jwtRequest.getUsername());

        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("❌ Usuario o credenciales inválidas para generar token");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        System.out.println("✅ Token generado correctamente para: " + jwtRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }
    /**
     * ⚙️ Método interno de autenticación
     */
    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("🚫 Usuario deshabilitado: " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("❌ Credenciales inválidas: " + e.getMessage());
        }
    }

    /**
     * 👤 Devuelve el usuario actualmente autenticado
     */
    /* @GetMapping("/actual-user")
    public ResponseEntity<Usuario> obtenerUsuarioActual(Principal principal) {
        Usuario usuario = (Usuario) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(usuario);
    } */
    @GetMapping("/actual-user")
    public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
        Usuario usuario = (Usuario) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());

        // Aseguramos que los roles estén inicializados
        Set<UsuarioRol> roles = usuario.getUsuarioRoles();

        List<String> nombresRoles = roles.stream()
                .map(ur -> ur.getRol().getRolNombre()) // <- usa rolNombre o nombre, según lo que uses
                .toList();

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", usuario.getId());
        userData.put("username", usuario.getUsername());
        userData.put("nombre", usuario.getNombre());
        userData.put("apellido", usuario.getApellido());
        userData.put("email", usuario.getEmail());
        userData.put("telefono", usuario.getTelefono());
        userData.put("perfil", usuario.getPerfil());
        userData.put("enabled", usuario.getEnabled());
        userData.put("roles", nombresRoles);

        return ResponseEntity.ok(userData);
    }




}
