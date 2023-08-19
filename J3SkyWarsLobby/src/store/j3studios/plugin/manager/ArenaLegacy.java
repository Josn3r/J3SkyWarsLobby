package store.j3studios.plugin.manager;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;

import store.j3studios.plugin.SWL;
import store.j3studios.plugin.utils.socket.ArenaSocketTask;

public class ArenaLegacy {
		
	public boolean addPlayer(Player p, String arena) {
		ArenaSocketTask as = SWL.get().getAM().getSocketByServer("arena");
		
		if (as == null) {
			SWL.debug("Error al intentar agregar al jugador en la arena!");
			if (!SWL.get().getAM().getSocketByServer().isEmpty()) {
				for (String str : SWL.get().getAM().getSocketByServer().keySet()) {
					SWL.debug("Sockets server: " + str);
				}
			} else {
				SWL.debug("No hay sockets by server registrados");
			}
			return false;
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("type", "PLD");
		json.addProperty("uuid", p.getUniqueId().toString());
		json.addProperty("arena_identifier", arena);
		as.getOut().println(json.toString());
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF("arena");
		p.sendPluginMessage(SWL.get(), "BungeeCord", out.toByteArray());
		return true;
	}

}
