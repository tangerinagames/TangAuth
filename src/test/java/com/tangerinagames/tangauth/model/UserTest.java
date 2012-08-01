package com.tangerinagames.tangauth.model;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserTest {

    @Mock private EbeanServer database;
    @Mock private Query<User> query;
    @Mock private ExpressionList<User> expression;
    @Mock private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User.setDatabase(database);
    }

    @Test
    public void shouldHaveAIdField() throws NoSuchFieldException {
        Field id = User.class.getDeclaredField("id");
        assertTrue(id.isAnnotationPresent(Id.class));
    }

    @Test
    public void shouldBeAEntity() {
        assertTrue(User.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void shouldCreateAUser() {
        when(database.createEntityBean(User.class)).thenReturn(new User());

        String userName = "TangZero";
        String password = "1234";
        Date lastLogin = new Date();

        User user = User.create(userName, password, lastLogin);

        assertEquals(userName, user.getUserName());
        assertEquals(password, user.getPassword());
        assertEquals(lastLogin, user.getLastLogin());

        verify(database).createEntityBean(User.class);
    }

    @Test
    public void shouldFindAUserByUserName() {
        String userName = "TangZero";

        when(database.find(User.class)).thenReturn(query);
        when(query.where()).thenReturn(expression);
        when(expression.ieq("userName", userName)).thenReturn(expression);
        when(expression.findUnique()).thenReturn(user);

        assertEquals(user, User.findByUserName(userName));
    }
}
