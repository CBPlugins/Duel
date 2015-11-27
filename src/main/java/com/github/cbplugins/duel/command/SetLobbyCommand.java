package com.github.cbplugins.duel.command;

import com.github.cbplugins.duel.Duel;
import com.github.cbplugins.duel.data.Spawnpoint;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by CBPlugins on 27.11.2015.
 */
public class SetLobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args ) {
        if ( !(sender instanceof Player) ) {
            Duel.info( "Only players can execute the /setlobby command" );
            return true;
        }

        Player player = (Player) sender;
        if ( player.hasPermission( "duel.setup.setlobby" ) ) {
            Spawnpoint spawnpoint = new Spawnpoint( player.getLocation().add( 0, 1, 0 ) ); // Add 1 to the y value to prevent spawning in the ground
            Duel.getInstance().getMainConfig().setLobbySpawn( spawnpoint );
            Duel.getInstance().getMainConfig().saveConfig();
            player.sendMessage( "§8[§cDuel§8]: §3You successfully set the lobby spawn." );
        }
        return true;
    }
}
