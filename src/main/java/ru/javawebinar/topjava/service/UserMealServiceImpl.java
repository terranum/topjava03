package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    public void setRepository(UserMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        return repository.save(userMeal);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        UserMeal userMeal = repository.get(id);
        if (userMeal.getUser().getId() != userId) {
            throw new NotFoundException("User can delete only his meals");
        }
        repository.delete(id);
    }

    @Override
    public void deleteAllByUserId(int userId) {
        repository.getAllByUserId(userId);
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        UserMeal userMeal = repository.get(id);
        if (userMeal.getUser().getId() != userId) {
            throw new NotFoundException("User can get only his meals");
        }
        return userMeal;
    }

    @Override
    public List<UserMeal> getAllByUserId(int userId) throws NotFoundException {
        return repository.getAllByUserId(userId);
    }

    @Override
    public List<UserMeal> getFiltredByUserId(int userId, LocalTime startTime, LocalTime endTime) throws NotFoundException {
        return repository.getFiltredByUserId(userId, startTime, endTime);
    }

    @Override
    public void update(UserMeal userMeal, int userId) throws NotFoundException {
        if (userMeal.getUser().getId() != userId) {
            throw new NotFoundException("User can update only his meals");
        }
        repository.update(userMeal);
    }
}
