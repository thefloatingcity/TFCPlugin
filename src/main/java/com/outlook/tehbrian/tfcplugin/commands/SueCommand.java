package com.outlook.tehbrian.tfcplugin.commands;

import com.outlook.tehbrian.tfcplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SueCommand implements CommandExecutor {
    private final Main plugin;

    public SueCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            return false;
        } else if (args.length >= 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            String fullArgs = sb.toString();

            Bukkit.broadcastMessage(plugin.formatChat("msg_sue", sender.getName(), fullArgs));
        }

        return true;
    }
}
