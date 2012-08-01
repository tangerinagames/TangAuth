package com.tangerinagames.tangauth.commands;

import com.tangerinagames.tangauth.TangAuth;
import com.tangerinagames.tangauth.model.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(User.class)
public class RegisterTest {

    private Register register;

    @Mock private CommandSender sender;
    @Mock private Command command;
    @Mock private Player player;
    @Mock private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spy(User.class);
        register = new Register(new TangAuth());
    }

    @Test
    public void shouldReturnCommandDescriptionWhenInvalidNumberOfArguments() {
        String desc = "Register a new user";

        when(command.getDescription()).thenReturn(desc);

        assertFalse(register.onCommand(sender, command, "", new String[0]));
        verify(sender).sendMessage(desc);
    }

    @Test
    public void shouldOnlyAcceptPlayerCommands() {
        assertFalse(register.onCommand(sender, command, "", new String[] { "1234" }));
        verify(sender).sendMessage(ChatColor.RED + "Only Players");
    }

    @Test
    public void shouldNotRegisterAlreadyRegisteredPlayers() {
        String userName = "TangZero";

        when(player.getName()).thenReturn(userName);

        doReturn(true).when(User.class);
        User.exists(userName);

        assertFalse(register.onCommand(player, command, "", new String[] { "1234" }));
        verify(player).sendMessage(ChatColor.RED + "Already Registered");
    }

    @Test
    public void shouldRegisterAPlayer() throws Exception {
        String userName = "TangZero";

        when(player.getName()).thenReturn(userName);

        doReturn(false).when(User.class);
        User.exists(userName);

        doReturn(user).when(User.class);
        User.create(anyString(), anyString(), any(Date.class));

        assertTrue(register.onCommand(player, command, "", new String[] { "1234" }));
        verify(user).save();
        verify(player).sendMessage(ChatColor.GREEN + "Registered");
    }

}
