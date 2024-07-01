package vn.shortsoft.userservice.utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.dto.UserDto;

@Log4j2
public class JwtUtil {
    private static Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS); // 1hour
    private static Instant expirationTime24h = Instant.now().plus(24, ChronoUnit.HOURS); // 1hour
    private static String secretKey = "24q5PhwxA02MndFFZ9HZmeBQ2w54wU7TvQgux189O0gjqizOeSbLBnGcFsXvPqxx";

    public static String generateAccessToken(UserDto userDto) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .issueTime(new Date())
                .issuer("shortsoft.vn")
                .subject(null)
                .expirationTime(Date.from(expirationTime))
                .audience("123")

                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (KeyLengthException e) {
            log.info("Độ dài của Secret Key không đúng: ");
        } catch (JOSEException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    private boolean isValidDate(String token) {
        JWTClaimsSet claimsSet;
        try {
            claimsSet = JWTClaimsSet.parse(token);
            return claimsSet.getExpirationTime().before(new Date());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public static String generateRefreshToken(UserDto userDto) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .issueTime(new Date())
                .issuer("shortsoft.vn")
                .subject(null)
                .expirationTime(Date.from(expirationTime24h))
                .audience("123")

                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (KeyLengthException e) {
            log.info("Độ dài của Secret Key không đúng: ");
        } catch (JOSEException e) {
            log.info(e.getMessage());
        }
        return null;
    }
}
