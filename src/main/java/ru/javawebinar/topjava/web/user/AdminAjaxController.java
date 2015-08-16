package ru.javawebinar.topjava.web.user;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.UserUtil;
import ru.javawebinar.topjava.util.exception.ValidationException;
import ru.javawebinar.topjava.web.ExceptionInfoHandler;

import javax.validation.Valid;
import java.util.List;

/**
 * User: javawebinar.topjava
 */
@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController implements ExceptionInfoHandler {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo getTo(@PathVariable("id") int id) {
        return UserUtil.asTo(super.get(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        status.setComplete();
        if (userTo.getId() == 0) {
            super.create(UserUtil.createFromTo(userTo));
        } else {
            super.update(userTo);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void enabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enable(id, enabled);
    }
}
