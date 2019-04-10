package com.outlook.tehbrian.tfcplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.outlook.tehbrian.tfcplugin.Main;
import com.outlook.tehbrian.tfcplugin.Misc;
import org.bukkit.command.CommandSender;

@CommandAlias("help")
@Description("Custom /help for the server.")
public class CustomHelpCommand extends BaseCommand {

    private final Main main;

    public CustomHelpCommand(Main main) {
        this.main = main;
    }

    @Default
    public void onHelp(CommandSender sender) {
        sender.sendMessage(Misc.formatConfig("msg_help"));
    }

    @HelpCommand
    public void onHelpUnknown(CommandSender sender) {
        sender.sendMessage(Misc.formatConfig("msg_help_unknown"));
    }
}
