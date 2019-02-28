package com.outlook.tehbrian.tfcplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.outlook.tehbrian.tfcplugin.Main;
import com.outlook.tehbrian.tfcplugin.Misc;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

@CommandAlias("action")
public class ActionsCommand extends BaseCommand {

    private final Main main;

    public ActionsCommand(Main main) {
        this.main = main;
    }

    @Default
    @CommandAlias("emotes")
    @Subcommand("list")
    public void onList(CommandSender sender) {

    }

    @CommandAlias("launch")
    @CommandPermission("tfcplugin.launch")
    public void onLaunch(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.setVelocity(new Vector(0, 10, 0));
                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, SoundCategory.MASTER, 100000, 0.75F);

                Bukkit.broadcastMessage(Misc.formatConfig("msg_launch_themself", sender.getName()));
            } else {
                sender.sendMessage(Misc.formatConfig("msg_player_only"));
            }
        } else if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (args[0].equals("everyone")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setVelocity(new Vector(0, 10, 0));
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, SoundCategory.MASTER, 100000, 0.75F);
                }
                Bukkit.broadcastMessage(Misc.formatConfig("msg_launch_everyone", sender.getName()));
            } else if (target != null) {
                target.setVelocity(new Vector(0, 10, 0));
                target.playSound(target.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, SoundCategory.MASTER, 100000, 0.75F);

                Bukkit.broadcastMessage(Misc.formatConfig("msg_launch", sender.getName(), target.getName()));
            } else {
                sender.sendMessage(Misc.formatConfig("msg_not_online"));
            }
        }
    }

    @CommandAlias("launch")
    @CommandPermission("tfcplugin.zap")
    public void onZap(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.getWorld().strikeLightning(player.getLocation());

                Bukkit.broadcastMessage(Misc.formatConfig("msg_zap_themself", sender.getName()));
            } else {
                sender.sendMessage(Misc.formatConfig("msg_player_only"));
            }
        } else if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (args[0].equals("everyone")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getWorld().strikeLightning(player.getLocation());
                }
                Bukkit.broadcastMessage(Misc.formatConfig("msg_zap_everyone", sender.getName()));
            } else if (target != null) {
                target.getWorld().strikeLightning(target.getLocation());

                Bukkit.broadcastMessage(Misc.formatConfig("msg_zap", sender.getName(), target.getName()));
            } else {
                sender.sendMessage(Misc.formatConfig("msg_not_online"));
            }
        }
    }

    @CommandAlias("poke")
    @CommandPermission("tfcplugin.poke")
    public boolean onPoke(CommandSender sender, String[] args) {

        double maxY = main.getConfig().getDouble("poke_force.maxY");
        double minY = main.getConfig().getDouble("poke_force.minY");
        double maxXZ = main.getConfig().getDouble("poke_force.maxXZ");
        double minXZ = main.getConfig().getDouble("poke_force.minXZ");
        Random random = new Random();
        double randX = minXZ + random.nextDouble() * (maxXZ - minXZ);
        double randY = minY + random.nextDouble() * (maxY - minY);
        double randZ = minXZ + random.nextDouble() * (maxXZ - minXZ);
        Vector randomVector = new Vector(randX, randY, randZ);

        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.setVelocity(randomVector);

                Bukkit.broadcastMessage(Misc.formatConfig("msg_poke_themself", sender.getName()));
            } else {
                sender.sendMessage(Misc.formatConfig("msg_player_only"));
            }
        } else if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (args[0].equals("everyone")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setVelocity(randomVector);
                }
                Bukkit.broadcastMessage(Misc.formatConfig("msg_poke_everyone", sender.getName()));
            } else if (target != null) {
                target.setVelocity(randomVector);

                Bukkit.broadcastMessage(Misc.formatConfig("msg_poke", sender.getName(), target.getName()));
            } else {
                sender.sendMessage(Misc.formatConfig("msg_not_online"));
            }
        }
        return true;
    }
}
