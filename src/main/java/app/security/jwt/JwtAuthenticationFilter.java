package app.security.jwt;

import app.service.PersonServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final PersonServiceImpl psi;
    private final JwtTokenServiceImpl jwt;

    public JwtAuthenticationFilter(PersonServiceImpl psi, JwtTokenServiceImpl jwt) {
        this.psi = psi;
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            extractTokenFromRequest(httpServletRequest)
                    .flatMap(jwt::tokenToClaim)
                    .map(jwt::extractUserIdFromClaims)
                    .map(psi::getById)
                    .map(ud -> new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities())) // UsernamePasswordAuthenticationToken
                    .ifPresent(auth -> {
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
    }

}

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        final String auth_header = request.getHeader(Const.AUTH_HEADER);

        if (StringUtils.hasText(auth_header)
                && auth_header.startsWith(Const.AUTH_BEARER)) {
            return Optional.of(auth_header.substring(Const.AUTH_BEARER.length())); // cut the "Bearer " substring
        }
        String par_token = request.getParameter(Const.AUTH_TOKEN);
        if (!StringUtils.isEmpty(par_token)) {
            return Optional.of(par_token.substring(Const.AUTH_BEARER.length()));
        }
        return Optional.empty();
    }
}
