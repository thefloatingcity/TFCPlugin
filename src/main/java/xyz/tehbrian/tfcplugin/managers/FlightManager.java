package xyz.tehbrian.tfcplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.tehbrian.tfcplugin.TFCPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FlightManager {

    final private static Set<UUID> playerCanBypassFly = new HashSet<>();

    private FlightManager() {}

    public static boolean getPlayerCanBypassFly(Player player) {
        return playerCanBypassFly.contains(player.getUniqueId());
    }

    public static void setPlayerCanBypassFly(Player player, boolean bool) {
        if (player.hasPermission("tfcplugin.core.fly") && bool) {
            playerCanBypassFly.add(player.getUniqueId());
            enableFlight(player);
        } else {
            playerCanBypassFly.remove(player.getUniqueId());
            disableFlight(player);
        }
    }

    public static boolean toggleFlight(Player player) {
        boolean bool = getPlayerCanBypassFly(player);
        setPlayerCanBypassFly(player, !bool);
        return !bool;
    }

    public static void enableFlight(Player player) {
        if (player.hasPermission("tfcplugin.core.fly") && getPlayerCanBypassFly(player)) {
            Bukkit.getScheduler().runTask(TFCPlugin.getInstance(), () -> {
                player.setAllowFlight(true);
                player.setFlying(true);
            });
        }
    }

    public static void disableFlight(Player player) {
        if (!player.hasPermission("tfcplugin.core.fly") || !getPlayerCanBypassFly(player)) {
            Bukkit.getScheduler().runTask(TFCPlugin.getInstance(), () -> {
                player.setAllowFlight(false);
                player.setFlying(false);
            });
        }
    }
}
