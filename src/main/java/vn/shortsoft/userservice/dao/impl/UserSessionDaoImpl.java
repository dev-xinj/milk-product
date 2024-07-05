package vn.shortsoft.userservice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import vn.shortsoft.userservice.dao.UserSessionDao;
import vn.shortsoft.userservice.model.UserSession;
import vn.shortsoft.userservice.repository.UserSessionRepository;

@Component
public class UserSessionDaoImpl implements UserSessionDao {

    @Autowired
    UserSessionRepository userSessionRepository;

    @Override
    public void cancelUserSession(Boolean a, Boolean b, String sessionId) {
        userSessionRepository.cancelSession(a, b, sessionId);
    }

    @Override
    @Transactional
    public UserSession getBySessionId(String sessionId) {
        return userSessionRepository.findBySessionId(sessionId).orElse(null);
    }

    @Override
    public Long save(UserSession userSession) {
        return userSessionRepository.save(userSession).getId();
    }

}
