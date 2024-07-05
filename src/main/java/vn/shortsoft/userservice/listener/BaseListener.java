package vn.shortsoft.userservice.listener;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.model.BaseEntity;

@Log4j2
public class BaseListener {
    @PrePersist
    public void setDatePersist(BaseEntity baseEntity) {
        String userName = getContext(t -> t.getName());
        baseEntity.setCreatedDate(new Timestamp(new Date().getTime()));
        baseEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
        baseEntity.setCreatedBy(userName);
        baseEntity.setUpdatedBy(userName);
        log.info("Create current Persist");
    }

    @PreUpdate
    public void setDateUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
        baseEntity.setUpdatedBy(getContext(t -> t.getName()));
        log.info("Update current Persist");
    }

    public <T> T getContext(Function<Authentication, T> funcion) {
        return funcion.apply(SecurityContextHolder.getContext().getAuthentication());
    }
}
