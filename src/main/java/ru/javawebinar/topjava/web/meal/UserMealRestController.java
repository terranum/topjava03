package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.web.ExceptionInfoHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(value = UserMealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMealRestController extends AbstractUserMealController implements ExceptionInfoHandler {
    public static final String REST_URL = "/rest/profile/meals";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        super.deleteAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMeal> createWithLocation(@RequestBody UserMeal meal) {
        UserMeal created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriOfNewResource);

        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public List<UserMealWithExceed> getBetween(
            @RequestParam(value = "startDateTime") LocalDateTime startDateTime,
            @RequestParam(value = "endDateTime") LocalDateTime endDateTime) {
        return super.getBetween(startDateTime.toLocalDate(), startDateTime.toLocalTime(), endDateTime.toLocalDate(), endDateTime.toLocalTime());
    }
}
