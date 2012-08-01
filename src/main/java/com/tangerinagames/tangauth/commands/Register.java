package com.tangerinagames.tangauth.commands;

import com.tangerinagames.tangauth.TangAuth;
import com.tangerinagames.tangauth.model.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class Register implements CommandExecutor {

    private TangAuth plugin;

    public Register(TangAuth plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Boolean success = false;

        if (args.length < 1) {
            sender.sendMessage(command.getDescription());

        } else if (!Player.class.isInstance(sender)) {
            sender.sendMessage(ChatColor.RED + "Only Players");

        } else {
            Player player = (Player) sender;

            if (User.exists(player.getName())) {
                sender.sendMessage(ChatColor.RED + "Already Registered");

            } else {
                try {
                    User.create(player.getName(), args[0], new Date()).save();
                    sender.sendMessage(ChatColor.GREEN + "Registered");
                    success = true;
                } catch (Exception e) {
                    plugin.getLogger().severe(e.getMessage());
                }
            }
        }

        return success;
    }
}
