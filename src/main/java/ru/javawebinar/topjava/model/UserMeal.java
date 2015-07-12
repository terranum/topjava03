package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@Entity
@Table(name = "MEALS")
@NamedQueries({
        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal SET description=:description,calories=:calories,dateTime=:dateTime WHERE id=:id AND user.id=:userId"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.id =:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT um FROM UserMeal um WHERE um.id =:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.ALL_DELETE, query = "DELETE FROM UserMeal um WHERE um.user.id=:userId"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.ALL_BETWEEN, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId AND um.dateTime >=:startDate AND um.dateTime<=:endDate ORDER BY um.dateTime DESC"),

})
public class UserMeal extends BaseEntity {
    public static final String DELETE = "UserMeal.delete";
    public static final String UPDATE = "UserMeal.update";
    public static final String GET = "UserMeal.get";
    public static final String ALL_DELETE = "UserMeal.deleteAll";
    public static final String ALL_BETWEEN = "UserMeal.getBetween";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";

    @Column(name = "datetime", nullable = false)
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    protected LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    protected String description;

    @Column(name = "calories", nullable = false)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotEmpty
    private User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + dateTime + ", '" + description + "', calories:" + calories + ')';
    }
}
