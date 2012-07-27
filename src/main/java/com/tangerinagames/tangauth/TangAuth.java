package com.tangerinagames.tangauth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TangAuth extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onDisable() {
        super.onDisable();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        return super.getDatabaseClasses();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
