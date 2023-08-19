package store.j3studios.plugin.manager;

import java.util.HashMap;

import store.j3studios.plugin.SWL;
import store.j3studios.plugin.utils.socket.ArenaSocketTask;

public class ArenaManager {
	
	private final HashMap<String, ArenaSocketTask> socketByServer = new HashMap<>();
	
	public ArenaManager() {
	}
	
	public void registerServerSocket(String server, ArenaSocketTask task) {
		if (socketByServer.containsKey(server)) {
			socketByServer.replace(server, task);
			return;
		}
		socketByServer.put(server, task);
	}
	
	public ArenaSocketTask getSocketByServer (String server) {
		return SWL.get().getAM().socketByServer.getOrDefault(server, null);
	}
	
	public HashMap<String, ArenaSocketTask> getSocketByServer() {
		return socketByServer;
	}

}
