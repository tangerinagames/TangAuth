package com.tangerinagames.tangauth.model;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(User.class)
public class UserTest {

    @Mock private EbeanServer database;
    @Mock private Query<User> query;
    @Mock private ExpressionList<User> expression;
    @Mock private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spy(User.class);
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

    @Test
    public void shouldDetermineIfUserExists() {
        String userName = "TangZero";

        doReturn(user).when(User.class);
        User.findByUserName(userName);

        assertTrue(User.exists(userName));

        doReturn(null).when(User.class);
        User.findByUserName(userName);

        assertFalse(User.exists(userName));
    }
}
