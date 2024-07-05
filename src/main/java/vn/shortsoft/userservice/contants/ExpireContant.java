package vn.shortsoft.userservice.contants;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ExpireContant {
    public static Instant EXPIRE_DATE = Instant.now().plus(1, ChronoUnit.HOURS);
    public static Instant EXPIRE_DATE24 = Instant.now().plus(24, ChronoUnit.HOURS);
}
