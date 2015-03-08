package io.delr3ves.smashtechtalk.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author Sergio Arroyo - @delr3ves
 */
@Data
public class User {
    public enum Genre {MALE, FEMALE}

    private String name;
    private String surname;
    private Genre genre;
    private String email;
    private Boolean receiveOffers = false;
    private String password;
    private String region;
    private DateTime birthday;

    public String getBirthdayDay() {
        return birthdayToString("dd");
    }


    public String getBirthdayMonth() {
        return birthdayToString("MM");
    }

    public String getBirthdayYear() {
        return birthdayToString("YYYY");
    }

    private String birthdayToString(String mask) {
        if (birthday != null) {
            return birthday.toString(mask);
        }
        return "";
    }

}
