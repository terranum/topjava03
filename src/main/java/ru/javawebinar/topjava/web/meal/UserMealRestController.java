package ru.javawebinar.topjava.web.meal;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);

    @Autowired
    private UserMealServiceImpl service;

    public List<UserMeal> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAllByUserId " + userId);
        return service.getAllByUserId(userId);
    }

    public List<UserMeal> getFiltred(LocalTime startTime, LocalTime endTime) {
        int userId = LoggedUser.id();
        LOG.info("getFiltredByUserId " + userId + " " + startTime + " " + endTime);
        return service.getFiltredByUserId(userId, startTime, endTime);
    }

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get " + id);
        return service.get(id, userId);
    }

    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        return service.save(userMeal);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete " + id);
        service.delete(id, userId);
    }

    public void deleteAll() {
        int userId = LoggedUser.id();
        LOG.info("deleteAll " + userId);
        service.deleteAllByUserId(userId);
    }

    public void update(@NotNull UserMeal userMeal) {
        int userId = LoggedUser.id();
        LOG.info("update " + userMeal);
        service.update(userMeal, userId);
    }

}
