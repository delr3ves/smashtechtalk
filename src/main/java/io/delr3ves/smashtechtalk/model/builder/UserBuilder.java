package io.delr3ves.smashtechtalk.model.builder;

import io.delr3ves.smashtechtalk.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class UserBuilder {
    private Map<String, User> usersByDescription;
    private Random numericRandom;

    public UserBuilder() {
        numericRandom = new Random();
        usersByDescription = new HashMap<>();
        usersByDescription.put("valid-user", createValidUser());
        usersByDescription.put("user-with-invalid-password", createUserWithInvalidEmail());
        usersByDescription.put("empty-user", createEmptyUser());
    }

    public User createUserByDescription(String description) {
        return usersByDescription.getOrDefault(description, new User());
    }

    public User createValidUser() {
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(numericRandom.nextInt(10) + 3));
        user.setSurname(RandomStringUtils.randomAlphabetic(numericRandom.nextInt(10) + 3));
        user.setEmail(RandomStringUtils.randomAlphanumeric(numericRandom.nextInt(10) + 1) + "@irrelevantemail.com");
        user.setGenre(User.Genre.FEMALE);
        user.setReceiveOffers(false);
        user.setPassword(RandomStringUtils.randomAscii(numericRandom.nextInt(10) + 6));
        user.setRegion("Alicante");
        user.setBirthday(new DateTime().minusYears(numericRandom.nextInt(70) + 14));
        return user;
    }

    public User createUserWithInvalidEmail() {
        User user = createValidUser();
        user.setEmail(RandomStringUtils.randomAscii(numericRandom.nextInt(10) + 1) + "@");
        return user;
    }

    public User createEmptyUser() {
        return new User();
    }
}
