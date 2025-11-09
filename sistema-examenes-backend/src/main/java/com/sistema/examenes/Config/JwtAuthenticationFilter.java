package com.sistema.examenes.Config;

import com.sistema.examenes.Services.ServiceImpl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 🚫 Rutas que NO requieren validación JWT
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        // puedes agregar más rutas públicas aquí
        return path.equals("/api/generate-token") ||
                // path.startsWith("/api/usuarios/") ||
                path.startsWith("/public/");
    }

    /**
     * 🔐 Filtro JWT principal
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        System.out.println("1... requestToken: "+requestTokenHeader);

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException jwtException) {
                System.out.println("⚠️ El token ha expirado");
            } catch (Exception exception) {
                System.out.println("⚠️ Error al procesar token: " + exception.getMessage());
            }
        } else if (requestTokenHeader != null) {
            System.out.println("❌ Token inválido, formato incorrecto");
        } else {
            // Normal durante login o rutas públicas
            System.out.println("ℹ️ No se encontró token en la cabecera (posiblemente login o ruta pública)");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

            if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("✅ Autenticación establecida para el usuario: " + username);
            } else {
                System.out.println("❌ El token no es válido");
            }
        }

        filterChain.doFilter(request, response);
    }
}
