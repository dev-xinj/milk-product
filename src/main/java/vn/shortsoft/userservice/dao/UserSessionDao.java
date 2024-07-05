package vn.shortsoft.userservice.dao;

import vn.shortsoft.userservice.model.UserSession;

public interface UserSessionDao {
    void cancelUserSession(Boolean a, Boolean b, String sessionId);

    UserSession getBySessionId(String sessionId);

    Long save(UserSession userSession);
}
