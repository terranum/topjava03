package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final UserMeal FIRST = new UserMeal(100002, LocalDateTime.parse("2015-01-01T09:39:52.178"), "Завтрак", 500);
    public static final UserMeal SECOND = new UserMeal(100003, LocalDateTime.parse("2015-07-01T18:19:22.088"), "Ужин", 700);
    public static final UserMeal THIRD = new UserMeal(100004, LocalDateTime.parse("2014-07-01T13:39:42.328"), "Обед", 520);

    static {
        FIRST.setUser(UserTestData.USER);
        SECOND.setUser(UserTestData.USER);
        THIRD.setUser(UserTestData.ADMIN);
    }

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
