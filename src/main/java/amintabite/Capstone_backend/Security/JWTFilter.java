package amintabite.Capstone_backend.Security;

import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Exceptions.UnauthorizedException;
import amintabite.Capstone_backend.Services.CustomUserDetailsService;
import amintabite.Capstone_backend.Services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authHeader.substring(7);

        try {
            UUID userId = UUID.fromString(jwtTools.extractSubject(accessToken));
            Utente found = customUserDetailsService.loadUserById(userId);


            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(found.getRole().name()));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    found, null, authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            throw new UnauthorizedException("Token non valido o scaduto. Effettua il login.");
        }
    }
}
