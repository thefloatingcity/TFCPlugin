package xyz.tehbrian.tfcplugin.booster;

import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.Material;

public class Booster {

    private final Location location;
    private final int size;
    private final Axis axis;

    public Booster(Location location, int size, Axis axis) {
        this.location = location;
        this.size = size;
        this.axis = axis;
    }

    public Location getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public Axis getAxis() {
        return axis;
    }

    public void generate() {
        for (double degrees = 0.0D; degrees < 360.0D; degrees += 10.0D) {
            double angle = Math.toRadians(degrees);

            double vx = location.getX() * Math.cos(angle) - location.getZ() * Math.sin(angle);
            double vy = location.getY() * Math.cos(angle) - location.getY() * Math.sin(angle);
            double vz = location.getX() * Math.sin(angle) + location.getZ() * Math.cos(angle);

            location.getWorld().getBlockAt((int) vx, (int) vy, (int) vz).setType(Material.YELLOW_TERRACOTTA);
        }
    }
}