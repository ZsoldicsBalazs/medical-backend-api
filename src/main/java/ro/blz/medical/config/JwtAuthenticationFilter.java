package ro.blz.medical.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.blz.medical.domain.User;
import ro.blz.medical.exceptions.ErrorResponse;
import ro.blz.medical.service.CustomUserDetailsService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        try {
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt); // ACTUALLY THIS IS AN EMAIL !

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { //if user email exists AND user isn't authenticated

                User userDetails = (User) userDetailsService.loadUserByUsername(username);         // Get USER FROM DB BY EMAIL !!

                if (jwtService.isTokenValid(jwt, userDetails)) {                            //check if user and token is valid

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)  // extend this with our request
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
        } catch (ExpiredJwtException ex) {

            sendErrorResponse(response, "Token expirat. Va rugam sa va autentificati din nou.");
            return;
        } catch (JwtException ex) {
            // Alte erori legate de JWT sau utilizator negasit
            sendErrorResponse(response, "Token invalid.");
            return;
        }

        filterChain.doFilter(request, response);        // Next filter to be executed

    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), message)));
    }

}
