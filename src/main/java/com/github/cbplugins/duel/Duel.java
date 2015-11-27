package com.github.cbplugins.duel;

import com.github.cbplugins.duel.command.SetLobbyCommand;
import com.github.cbplugins.duel.config.MainConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by CBPlugins on 27.11.2015.
 */
public class Duel extends JavaPlugin {
    /**
     * The instance of this minigame.
     */
    @Getter private static Duel instance;

    /**
     * Logs the specified message to the INFO channel.
     * @param message The message to log.
     */
    public static void info( String message ) {
        getInstance().getLogger().info( message );
    }

    /**
     * Logs the specified message to the WARNING channel.
     * @param message The message to log.
     */
    public static void warning( String message ) {
        getInstance().getLogger().warning( message );
    }

    /**
     * Logs the specified message to the SEVERE channel.
     * @param message The message to log.
     */
    public static void severe( String message ) {
        getInstance().getLogger().severe( message );
    }

    /**
     * The main config object to get needed config data.
     */
    @Getter private MainConfig mainConfig;

    @Override
    public void onEnable() {
        instance = this;

        /*---------------------- configuration ----------------------*/

        this.mainConfig = new MainConfig();

        /*---------------------- setup commands ----------------------*/

        getCommand( "setlobby" ).setExecutor( new SetLobbyCommand() );

        /*---------------------- game commands ----------------------*/

        /*---------------------- listener ----------------------*/

        /*---------------------- map system ----------------------*/

        /*---------------------- game system ----------------------*/
    }

    @Override
    public void onDisable() {
        if ( this.mainConfig != null ) {
            this.mainConfig.saveConfig();
        }
    }
}
