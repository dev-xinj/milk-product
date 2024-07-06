package vn.shortsoft.userservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import vn.shortsoft.userservice.model.UserSession;

@Repository
public class CustomUserSessionRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void getUserSession(Boolean isRevoked, Boolean isExpired, String sessionId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        // CriteriaQuery<UserSession> query = builder.createQuery(UserSession.class);
        CriteriaUpdate<UserSession> update = builder.createCriteriaUpdate(UserSession.class);
        Root<UserSession> root = update.from(UserSession.class);
        update.set(root.get("isRevoked"), isRevoked);
        update.set(root.get("isExpired"), isExpired);
        Predicate condition = builder.equal(root.get("sessionId"), sessionId);
        update.where(condition);
        em.createQuery(update).toString();
        em.createQuery(update).executeUpdate();
    }
}
