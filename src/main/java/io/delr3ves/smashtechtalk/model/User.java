package io.delr3ves.smashtechtalk.model;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * @author Sergio Arroyo - @delr3ves
 */
@Data
public class User {
    public enum Genre {
        MALE(0), FEMALE(1);

        public final int id;

        Genre(int id) {
            this.id = id;
        }

        public static Genre map(Integer pos) {
            for (Genre genre : Genre.values()) {
                if (pos.equals(genre.id)) {
                    return genre;
                }
            }
            return null;
        }
    }

    private String name = "";
    private String surname = "";
    private Genre genre;
    private String email = "";
    private Boolean receiveOffers = false;
    private String password = "";
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
