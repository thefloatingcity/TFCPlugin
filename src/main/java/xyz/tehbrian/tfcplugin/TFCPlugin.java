package xyz.tehbrian.tfcplugin;

import co.aikar.commands.ConditionFailedException;
import co.aikar.commands.PaperCommandManager;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.tehbrian.tfcplugin.commands.ActionCommand;
import xyz.tehbrian.tfcplugin.commands.CoreCommand;
import xyz.tehbrian.tfcplugin.commands.EmoteCommand;
import xyz.tehbrian.tfcplugin.commands.GamemodeCommand;
import xyz.tehbrian.tfcplugin.commands.ItemCommand;
import xyz.tehbrian.tfcplugin.commands.OntimeCommand;
import xyz.tehbrian.tfcplugin.commands.PianoCommand;
import xyz.tehbrian.tfcplugin.commands.RulesCommand;
import xyz.tehbrian.tfcplugin.commands.UtilCommand;
import xyz.tehbrian.tfcplugin.listeners.AntiBuildListener;
import xyz.tehbrian.tfcplugin.listeners.BuildingListener;
import xyz.tehbrian.tfcplugin.listeners.FlightListener;
import xyz.tehbrian.tfcplugin.listeners.MiscListener;
import xyz.tehbrian.tfcplugin.listeners.PianoListener;

public final class TFCPlugin extends JavaPlugin {

    private static TFCPlugin instance;
    private PaperCommandManager commandManager;
    private LuckPerms luckPermsApi;

    public TFCPlugin() {
        instance = this;
    }

    public static TFCPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        setUpConfig();
        setUpEvents();
        setUpCommandManager();

        if (!setUpLuckPermsApi()) {
            getLogger().severe("No LuckPerms dependency found! Disabling plugin..");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("See you later!");
    }

    private void setUpConfig() {
        saveDefaultConfig();
    }

    private void setUpEvents() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new AntiBuildListener(), this);
        pluginManager.registerEvents(new BuildingListener(this), this);
        pluginManager.registerEvents(new MiscListener(this), this);
        pluginManager.registerEvents(new FlightListener(), this);
        pluginManager.registerEvents(new PianoListener(this), this);
    }

    private void setUpCommandManager() {
        commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new ActionCommand(this));
        commandManager.registerCommand(new CoreCommand(this));
        commandManager.registerCommand(new EmoteCommand());
        commandManager.registerCommand(new GamemodeCommand());
        commandManager.registerCommand(new ItemCommand());
        commandManager.registerCommand(new OntimeCommand());
        commandManager.registerCommand(new PianoCommand());
        commandManager.registerCommand(new RulesCommand());
        commandManager.registerCommand(new UtilCommand());

        commandManager.enableUnstableAPI("help");

        commandManager.getCommandConditions().addCondition(Integer.class, "limits", (context, executionContext, value) -> {
            if (value == null) {
                return;
            }
            if (context.hasConfig("min") && context.getConfigValue("min", 0) > value) {
                throw new ConditionFailedException("Minimum value is " + context.getConfigValue("min", 0) + ".");
            }
            if (context.hasConfig("max") && context.getConfigValue("max", 10) < value) {
                throw new ConditionFailedException("Maximum value is " + context.getConfigValue("max", 10) + ".");
            }
        });
    }

    private boolean setUpLuckPermsApi() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) {
            return false;
        }
        luckPermsApi = provider.getProvider();
        return true;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    public LuckPerms getLuckPermsApi() {
        return luckPermsApi;
    }
}