package com.github.cbplugins.duel.data;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CBPlugins on 27.11.2015.
 */
public class Spawnpoint implements ConfigurationSerializable {
    /**
     * The x coordinate of the spawn point.
     */
    private double x;

    /**
     * The y coordinate of the spawn point.
     */
    private double y;

    /**
     * The z coordinate of the spawn point.
     */
    private double z;

    /**
     * The pitch of the view direction.
     */
    private float pitch;

    /**
     * The yaw of the view direction.
     */
    private float yaw;

    /**
     * The name of the world, this spawn point is in.
     */
    private String world;

    /**
     * Constructs a new Spawnpoint object by passing over a Location object.
     * @param location The location object.
     */
    public Spawnpoint( Location location ) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        this.world = location.getWorld().getName();
    }

    /**
     * Constructs a new Spawnpoint object by passing over a Map with data.
     * This is used for configuration serialization.
     * @param values The map containing location data.
     */
    public Spawnpoint( Map<String, Object> values ) {
        this.x = (Double) values.get( "x" );
        this.y = (Double) values.get( "y" );
        this.z = (Double) values.get( "z" );
        this.yaw = (Float) values.get( "yaw" );
        this.pitch = (Float) values.get( "pitch" );
        this.world = (String) values.get( "world" );
    }

    /**
     * Serializes this object to a Map of data.
     * This is used for serialization.
     * @return A map containing data to save to the config.
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put( "x", this.x );
        map.put( "y", this.y );
        map.put( "z", this.z );
        map.put( "pitch", this.pitch );
        map.put( "yaw", this.yaw );
        map.put( "world", this.world );
        return map;
    }
}
