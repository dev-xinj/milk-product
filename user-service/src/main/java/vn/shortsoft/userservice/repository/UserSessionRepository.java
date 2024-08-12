package vn.shortsoft.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import vn.shortsoft.userservice.model.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findBySessionId(String sessionId);

    @Modifying
    @Query("update UserSession s SET s.isRevoked = :isRevoked, s.isExpired = :isExpired WHERE s.sessionId = :sessionId")
    void cancelSession(@Param("isRevoked") Boolean isRevoked, @Param("isExpired") Boolean isExpired,
            @Param("sessionId") String sessionId);
}
