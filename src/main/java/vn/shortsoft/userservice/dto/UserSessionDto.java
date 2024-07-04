package vn.shortsoft.userservice.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSessionDto implements Serializable {
    private String sessionId;

    private Timestamp expirationTime;
    private Boolean isRevoked;
    private Boolean isExpired;
    private String accessToken;
    private String refreshToken;

}
