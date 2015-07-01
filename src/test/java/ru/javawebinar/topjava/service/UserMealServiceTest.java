package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(FIRST, service.get(FIRST.getId(), FIRST.getUser().getId()));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(FIRST.getId(), FIRST.getUser().getId());
        MATCHER.assertListEquals(Collections.singletonList(SECOND), service.getAll(FIRST.getUser().getId()));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(SECOND), service.getBetweenDates(
                FIRST.getDateTime().toLocalDate().plusDays(1),
                SECOND.getDateTime().toLocalDate().plusDays(1),
                FIRST.getUser().getId()));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(SECOND), service.getBetweenDateTimes(
                FIRST.getDateTime().plusHours(1),
                SECOND.getDateTime().plusHours(1),
                FIRST.getUser().getId()));
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> list = new ArrayList<>();
        list.add(FIRST);
        list.add(SECOND);
        MATCHER.assertListEquals(list, service.getAll(FIRST.getUser().getId()));
    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(SECOND.getUser().getId());
        MATCHER.assertListEquals(new ArrayList<>(), service.getAll(SECOND.getUser().getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal userMeal = new UserMeal(THIRD.getId(), LocalDateTime.now(), "Now", 600);
        userMeal.setUser(THIRD.getUser());
        MATCHER.assertEquals(userMeal, service.update(userMeal, THIRD.getId()));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = new UserMeal(0, LocalDateTime.now(), "Now", 600);
        userMeal.setUser(FIRST.getUser());
        service.save(userMeal, userMeal.getUser().getId());
        MATCHER.assertListEquals(Arrays.asList(FIRST, SECOND, userMeal), service.getAll(FIRST.getUser().getId()));
    }
}