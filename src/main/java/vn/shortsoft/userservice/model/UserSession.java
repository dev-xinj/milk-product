package vn.shortsoft.userservice.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "s_user_session")
public class UserSession extends BaseEntity {

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "expiration_time")
    private Timestamp expirationTime;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserRoles))
            return false;
        return super.getId() != null && super.getId().equals(((UserRoles) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
