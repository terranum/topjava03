package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "Завтрак", 300),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "Завтрак", 200),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 2500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList,
                                                                        LocalTime startTime,
                                                                        LocalTime endTime,
                                                                        int caloriesPerDay) {
        Map<LocalDateTime, Integer> caloriesSummingByDay = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDateTime, Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(uM -> TimeUtil.isBetween(uM.getDateTime().toLocalTime(), startTime, endTime))
                .map(uM -> new UserMealWithExceed(
                        uM.getDateTime(),
                        uM.getDescription(),
                        uM.getCalories(),
                        caloriesSummingByDay.get(uM.getDateTime()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
