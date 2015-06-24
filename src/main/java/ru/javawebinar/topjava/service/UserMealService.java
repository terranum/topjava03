package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    public UserMeal save(UserMeal userMeal);

    public void delete(int id, int userId) throws NotFoundException;

    public void deleteAllByUserId(int userId) throws NotFoundException;

    public UserMeal get(int id, int userId) throws NotFoundException;

    public List<UserMeal> getAllByUserId(int userId);

    public List<UserMeal> getFiltredByUserId(int userId, LocalTime startTime, LocalTime endTime) throws NotFoundException;

    public void update(UserMeal userMeal, int userId) throws NotFoundException;

}
