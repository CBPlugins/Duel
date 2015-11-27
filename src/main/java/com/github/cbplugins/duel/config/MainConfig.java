package com.github.cbplugins.duel.config;

import com.github.cbplugins.duel.Duel;
import com.github.cbplugins.duel.data.Spawnpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by CBPlugins on 27.11.2015.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MainConfig {
    /**
     * Whether or not this server will be in setup mode.
     */
    private boolean setup = true;

    /**
     * Wrapper containing the lobby spawn.
     */
    private Spawnpoint lobbySpawn = null;

    /**
     * Wrapper containing the first ingame spawn.
     */
    private Spawnpoint firstSpawn = null;

    /**
     * Wrapper containing the second ingame spawn.
     */
    private Spawnpoint secondSpawn = null;

    /**
     * Wrapper containing the spectator spawn.
     */
    private Spawnpoint spectatorSpawn = null;

    /**
     * Constructs a new MainConfig object and loads needed data.
     */
    public MainConfig() {
        try {
            if ( !Duel.getInstance().getDataFolder().exists() && !Duel.getInstance().getDataFolder().mkdir() ) {
                Duel.severe( "Couldn't create datafolder. Please double check your file system permissions." );
                Duel.severe( "Due to this error the server will shutdown automatically." );
                Bukkit.shutdown();
            }

            File config = new File( Duel.getInstance().getDataFolder(), "config.yml" );
            if ( !config.exists() ) {
                if ( !config.createNewFile() ) {
                    Duel.severe( "Couldn't create config file. Please double check your file system permissions." );
                    Duel.severe( "Due to this error the server will shutdown automatically." );
                    Bukkit.shutdown();
                } else {
                    // Initialize the spawn points
                    // We will set them to the defaults world spawn location to prevent errors
                    Location spawnLocation = Bukkit.getWorlds().get( 0 ).getSpawnLocation();
                    this.lobbySpawn = new Spawnpoint( spawnLocation );
                    this.firstSpawn = new Spawnpoint( spawnLocation );
                    this.secondSpawn = new Spawnpoint( spawnLocation );
                    this.spectatorSpawn = new Spawnpoint( spawnLocation );
                    saveConfig();
                }
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        loadConfig();
    }

    /**
     * Saves the values to the configuration file.
     */
    public void saveConfig() {
        try {
            File file = new File( Duel.getInstance().getDataFolder(), "config.yml" );
            FileConfiguration config = YamlConfiguration.loadConfiguration( file );

            config.set( "setup", this.setup );
            config.set( "spawns.lobby", this.lobbySpawn );
            config.set( "spawns.first", this.firstSpawn );
            config.set( "spawns.second", this.secondSpawn );
            config.set( "spawns.spectator", this.spectatorSpawn );

            config.save( file );
            Duel.info( "Configuration file saved" );
        } catch ( Exception exception ) {
            Duel.severe( "Error while trying to save configuration file" );
            exception.printStackTrace();
        }
    }

    /**
     * Loads the values from the configuration file.
     */
    public void loadConfig() {
        File file = new File( Duel.getInstance().getDataFolder(), "config.yml" );
        FileConfiguration config = YamlConfiguration.loadConfiguration( file );

        this.setup = config.getBoolean( "setup" );
        this.lobbySpawn = (Spawnpoint) config.get( "spawns.lobby" );
        this.firstSpawn = (Spawnpoint) config.get( "spawns.first" );
        this.secondSpawn = (Spawnpoint) config.get( "spawns.second" );
        this.spectatorSpawn = (Spawnpoint) config.get( "spawns.spectator" );

        Duel.info( "Configuration file loaded" );
    }
}
