package com.tangerinagames.tangauth.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Model.class)
public class UserTest {

    @Before
    public void setUp() {
        mockStatic(Model.class);
    }

    @Test
    public void shouldCreateAUser() {
        when(Model.create(User.class)).thenReturn(new User());

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
        User user = new User();
        when(Model.find(User.class, key)).thenReturn(user);

        assertEquals(user, User.find(key));
    }
}
