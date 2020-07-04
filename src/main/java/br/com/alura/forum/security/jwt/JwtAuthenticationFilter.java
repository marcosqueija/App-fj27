package br.com.alura.forum.security.jwt;

import br.com.alura.forum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";

    private TokenManager tokenManager;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = getToken(req.getHeader("Authorization"));
        if (tokenManager.isValid(token)) {
            Long userId = tokenManager.getUserIdFromToken(token);
            UserDetails user = userService.loadUserById(userId);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        }
        chain.doFilter(req, res);
    }

    private String getToken(String bearerToken) {
        String jwt = null;
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            jwt =  bearerToken.replace(BEARER_PREFIX, "");
        }
        return jwt;
    }

}
