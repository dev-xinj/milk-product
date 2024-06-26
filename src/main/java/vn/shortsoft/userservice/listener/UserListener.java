package vn.shortsoft.userservice.listener;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.model.User;

@Log4j2
public class UserListener {

    @PrePersist
    public void setDatePersist(User user) {
        user.setCreatedDate(new Timestamp(new Date().getTime()));
        user.setUpdatedDate(new Timestamp(new Date().getTime()));
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        user.setCreatedBy(username);
        user.setUpdatedBy(username);
        log.info("Create current Persist");
    }

    @PreUpdate
    public void setDateUpdate(User user) {
        user.setUpdatedDate(new Timestamp(new Date().getTime()));
        log.info("Update current Persist");
    }

}
