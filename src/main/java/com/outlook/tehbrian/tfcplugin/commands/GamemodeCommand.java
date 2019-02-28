package com.outlook.tehbrian.tfcplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.outlook.tehbrian.tfcplugin.Main;
import com.outlook.tehbrian.tfcplugin.Misc;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gm|gamemode")
@CommandPermission("tfcplugin.gamemode")
public class GamemodeCommand extends BaseCommand {

    private final Main main;

    public GamemodeCommand(Main main) {
        this.main = main;
    }

    @HelpCommand
    public void onList(CommandSender sender) {
        sender.sendMessage("Testing! :D");
    }

    @Subcommand("survival|s|0")
    @CommandAlias("gms")
    public void onSurvival(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(Misc.formatConfig("msg_gamemode_change", "Survival"));
    }

    @Subcommand("creative|c|1")
    @CommandAlias("gmc")
    public void onCreative(Player player) {
        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage(Misc.formatConfig("msg_gamemode_change", "Creative"));
    }

    @Subcommand("adventure|a|2")
    @CommandAlias("gma")
    public void onAdventure(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(Misc.formatConfig("msg_gamemode_change", "Adventure"));
    }
}
