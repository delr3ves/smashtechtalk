package io.delr3ves.smashtechtalk.services;

import io.delr3ves.smashtechtalk.model.User;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public interface UserService {

    /**
     * Register new user in the platform.
     *
     * @param user
     * @return
     */
    User register(User user);
}
