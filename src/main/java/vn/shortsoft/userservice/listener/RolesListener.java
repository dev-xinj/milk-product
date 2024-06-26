package vn.shortsoft.userservice.listener;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.model.Roles;

@Log4j2
public class RolesListener {
    @PrePersist
    public void setDatePersist(Roles roles) {
        roles.setCreatedDate(new Timestamp(new Date().getTime()));
        roles.setUpdatedDate(new Timestamp(new Date().getTime()));
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        roles.setUpdatedBy(username);
        roles.setCreatedBy(username);
        log.info("Create current Persist");
    }

    @PreUpdate
    public void setDateUpdate(Roles roles) {
        roles.setUpdatedDate(new Timestamp(new Date().getTime()));
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        roles.setUpdatedBy(username);
        log.info("Update current Persist");
    }
}
