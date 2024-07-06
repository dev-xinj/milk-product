package vn.shortsoft.userservice.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.shortsoft.userservice.model.UserSession;
import vn.shortsoft.userservice.repository.UserSessionRepository;
import vn.shortsoft.userservice.security.CustomUserDetailsService;
import vn.shortsoft.userservice.utils.JwtUtil;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;
        final String sessionId;
        if (!StringUtils.hasLength(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userName = jwtUtil.extractUserName(jwtToken);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            boolean is = jwtUtil.isValidToken(jwtToken, userDetails);
            sessionId = jwtUtil.extractSessionId(jwtToken);

            UserSession userSession = userSessionRepository.findBySessionId(sessionId).orElse(null);
            Boolean isValidSession = false;
            if (userSession != null) {
                isValidSession = userSession.getIsExpired() == false && userSession.getIsRevoked() == false;
            }

            if (jwtUtil.isVerifyToken(jwtToken) && jwtUtil.isValidToken(jwtToken, userDetails) && isValidSession) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(userToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }

}
