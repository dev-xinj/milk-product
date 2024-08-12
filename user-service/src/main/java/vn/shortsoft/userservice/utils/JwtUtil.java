package vn.shortsoft.userservice.utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.contants.ExpireContant;
import vn.shortsoft.userservice.contants.JwtContant;
import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.model.UserSession;

@Log4j2
@Component
@Data
public class JwtUtil {

    public static String generateAccessToken(UserDto userDto) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .issueTime(new Date())
                .issuer("shortsoft.vn")
                .subject(userDto.getUserName())
                .claim("sessionId", userDto.getUserSession().getSessionId())
                .expirationTime(Date.from(ExpireContant.EXPIRE_DATE))
                .audience("123")
                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(JwtContant.SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (KeyLengthException e) {
            log.info("Độ dài của Secret Key không đúng: ");
        } catch (JOSEException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    // Refresh Token
    public static String generateRefreshToken(Map<String, Object> claim, UserDto userDto) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .issueTime(new Date())
                .issuer("shortsoft.vn")
                .subject(userDto.getUserName())
                .expirationTime(Date.from(ExpireContant.EXPIRE_DATE24))
                .audience("123")
                .claim("data", claim)
                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(JwtContant.SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isValidExpiry(token);
    }


    private boolean isValidExpiry(String token) {
        try {
            return extractClaims(token, t -> t.getExpirationTime()).before(new Date());
        } catch (JOSEException | ParseException e) {
            log.info(e.getMessage());
        }
        return false;
    }

    public boolean isVerifyToken(String token) {
        try {
            JWSVerifier jwsVerifier = new MACVerifier(JwtContant.SECRET_KEY.getBytes());
            return JWSObject.parse(token).verify(jwsVerifier);
        } catch (JOSEException | ParseException e) {
            log.info(e.getMessage());
        }
        return false;
    }

    private static <T> T extractClaims(String token, Function<JWTClaimsSet, T> claimTFunction)
            throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        return claimTFunction.apply(JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject()));
    }

    public static String extractUserName(String token) {
        try {
            return extractClaims(token, JWTClaimsSet::getSubject);
        } catch (JOSEException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String extractSessionId(String token) {
        try {
            return extractClaims(token, t -> t.getClaim("sessionId").toString());
        } catch (JOSEException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
