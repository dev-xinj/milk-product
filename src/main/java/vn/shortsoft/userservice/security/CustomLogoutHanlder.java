package vn.shortsoft.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.dao.UserSessionDao;
import vn.shortsoft.userservice.model.UserSession;
import vn.shortsoft.userservice.repository.CriRepository;
import vn.shortsoft.userservice.utils.JwtUtil;

@Component
@Log4j2
public class CustomLogoutHanlder implements LogoutHandler {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserSessionDao userSessionDao;

    @Autowired
    CriRepository criRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String sessionId;

        if (!StringUtils.hasLength(authHeader)) {
            return;
        }

        jwtToken = authHeader.substring(7);

        sessionId = jwtUtil.extractSessionId(jwtToken);
        // UserSession userSession = userSessionDao.getBySessionId(sessionId);
        if (sessionId != null) {
            // userSession.setIsExpired(true);
            // userSession.setIsRevoked(true);
            // userSessionDao.save(userSession);

            criRepository.getUserSession(true, true, sessionId);
            SecurityContextHolder.clearContext();
        }
        log.info("logout successfully");

    }

}
