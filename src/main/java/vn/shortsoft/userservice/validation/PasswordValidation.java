package vn.shortsoft.userservice.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class PasswordValidation {

    public static boolean validateAtLeastOneLetterAndOne(String password) {
        if (StringUtils.hasLength(password)) {
            String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
