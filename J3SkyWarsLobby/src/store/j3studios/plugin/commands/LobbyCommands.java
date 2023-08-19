package store.j3studios.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import store.j3studios.plugin.SWL;
import store.j3studios.plugin.manager.ArenaLegacy;

public class LobbyCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			SWL.debug("Solo puede ejecutar comando siendo jugador.");
			return true;
		}
		
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("testing")) {
			new ArenaLegacy().addPlayer(p, args[0]);
			return true;
		}
		
		return true;
	}

}
