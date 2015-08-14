package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.DateTimeFilter;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.ValidationException;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.TimeUtil.*;

/**
 * User: javawebinar.topjava
 */
@RestController
@RequestMapping("/ajax/profile/meals")
public class UserMealAjaxController extends AbstractUserMealController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@Valid UserMeal meal, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        } else {
            status.setComplete();
            if (meal.getId() == 0) {
                super.create(meal);
            } else {
                super.update(meal, meal.getId());
            }
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> filterWithExceed(DateTimeFilter filter) {
        return super.getBetween(
                startDate(filter.getStartDate()), toTime(filter.getStartTime(), LocalTime.MIN),
                endDate(filter.getEndDate()), toTime(filter.getEndTime(), LocalTime.MAX));
    }
}
