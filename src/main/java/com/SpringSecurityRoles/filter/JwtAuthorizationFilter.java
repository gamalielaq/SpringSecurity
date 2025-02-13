package com.SpringSecurityRoles.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SpringSecurityRoles.constants.SecurityConstant;
import com.SpringSecurityRoles.utility.JWTTokenProvider;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JWTTokenProvider jwtTokenProvider;
    
    @Autowired
    public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    // filter:--> es para autorizar o no autorizar cualquier solicitud
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
            response.setStatus(HttpStatus.OK.value()); // No hcemos nada
        } else {
            String autorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            // Verificar si el encabezado de autorización está presente y si contiene el prefijo adecuado para el token JWT.
            // Si no está presente o no empieza con el prefijo esperado (ej. "Bearer "), simplemente pasamos la solicitud al siguiente filtro
            // sin hacer ninguna validación de JWT. Esto permite que las rutas públicas puedan ser accedidas sin necesidad de un token.
            if (autorizationHeader == null || !autorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response); //--> Permite pasar la solicitud al siguiente filtro sin validación de JWT
                return;
            }
            String token = autorizationHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
            String username = jwtTokenProvider.getSubjet(token);
            if (jwtTokenProvider.isTokenValid(username, token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) { // Token Valido y no tener un autenticacion en el contexto de seguridad
                List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
                SecurityContextHolder.getContext().setAuthentication(authentication); // Usuario Athenticado
            } else {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

}
