package com.tangerinagames.tangauth.model;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.Entity;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Model.class)
public class UserTest {

    @Mock
    private EbeanServer database;

    @Mock
    private Query<User> query;

    @Mock
    private User user;

    @Before
    public void setUp() {
        spy(Model.class);
        User.setDatabase(database);
    }

    @Test
    public void shouldBeAModel() {
        assertEquals(Model.class, User.class.getSuperclass());
    }

    @Test
    public void shouldBeAEntity() {
        assertTrue(User.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void shouldCreateAUser() {
        doReturn(new User()).when(Model.class);
        Model.create(User.class);

        String userName = "TangZero";
        String password = "1234";
        Date lastLogin = new Date();

        User user = User.create(userName, password, lastLogin);

        assertEquals(userName, user.getUserName());
        assertEquals(password, user.getPassword());
        assertEquals(lastLogin, user.getLastLogin());

        verifyStatic();
        Model.create(User.class);
    }

    @Test
    public void shouldFindAUser() {
        long key = 1;

        doReturn(user).when(Model.class);
        Model.find(User.class, key);

        assertEquals(user, User.find(key));
    }

    @Test
    public void shouldFindAUserByUserName() {
        String userName = "TangZero";

        when(database.createNamedQuery(User.class, "findByUserName")).thenReturn(query);
        when(query.findUnique()).thenReturn(user);

        assertEquals(user, User.findByUserName(userName));
        verify(query).setParameter("userName", userName);
    }
}
