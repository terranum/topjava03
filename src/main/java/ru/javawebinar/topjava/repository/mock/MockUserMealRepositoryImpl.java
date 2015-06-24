package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);

    @Override
    public UserMeal save(UserMeal userMeal) {
        LOG.info("save " + userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return true;
    }

    @Override
    public UserMeal get(int id) {
        LOG.info("get " + id);
        return null;
    }

    @Override
    public List<UserMeal> getAllByUserId(int userId) {
        LOG.info("getAllByUserId " + userId);
        return Collections.emptyList();
    }

    @Override
    public List<UserMeal> getFiltredByUserId(int userId, LocalTime startTime, LocalTime endTime) {
        LOG.info("getFiltredByUserId " + userId + " " + startTime + " " + endTime);
        return getAllByUserId(userId)
                .stream()
                .filter((uM) -> TimeUtil.isBetween(uM.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserMeal userMeal) {
        LOG.info("update " + userMeal);
    }
}
