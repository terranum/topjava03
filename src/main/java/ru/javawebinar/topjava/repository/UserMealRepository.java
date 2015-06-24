package ru.javawebinar.topjava.repository;

import com.sun.istack.internal.NotNull;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {

    UserMeal save(@NotNull UserMeal userMeal);

    // false if not found
    boolean delete(int id);

    // null if not found
    UserMeal get(int id);

    List<UserMeal> getAllByUserId(int userId);

    List<UserMeal> getFiltredByUserId(int userId,@NotNull LocalTime startTime,@NotNull LocalTime endTime);

    void update(@NotNull UserMeal userMeal);
}
