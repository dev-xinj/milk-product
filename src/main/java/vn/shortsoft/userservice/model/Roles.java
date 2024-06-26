package vn.shortsoft.userservice.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.shortsoft.userservice.listener.RolesListener;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@EntityListeners(RolesListener.class)
@Entity
@Table(name = "s_roles")
public class Roles extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRoles> userRoles;

    public void setUserRoles(UserRoles userRole) {
        if (userRole != null) {
            if (userRoles == null) {
                userRoles = new HashSet<>();
            }
            userRoles.add(userRole);
            userRole.setRole(this);
        }
    }
}
