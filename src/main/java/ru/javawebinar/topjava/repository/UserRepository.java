package ru.javawebinar.topjava.repository;

import com.sun.istack.internal.NotNull;
import ru.javawebinar.topjava.model.User;

import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public interface UserRepository {

    User save(@NotNull User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(@NotNull String email);

    List<User> getAll();
}
