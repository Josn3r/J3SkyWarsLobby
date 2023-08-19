package store.j3studios.plugin;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import store.j3studios.plugin.commands.LobbyCommands;
import store.j3studios.plugin.manager.ArenaManager;
import store.j3studios.plugin.utils.Tools;
import store.j3studios.plugin.utils.socket.SocketServer;

public class SWL extends JavaPlugin {
    
    private static SWL ins;
    private SocketServer server;
    
    private ArenaManager am;
    
    public void onEnable() {
        ins = this;          
        
        // LOAD CONFIG
        this.getConfig();
        this.saveDefaultConfig();
        

        // LOAD MANAGERS
        am = new ArenaManager();
        
        // START SOCKET SERVER
        if (this.getConfig().getBoolean("server.socket-connector.enable")) {
            Integer port = this.getConfig().getInt("server.socket-connector.port");
            socketStart(port);
        }
        
        
        // LOAD BUNGEECORD SUPPORT
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // LOAD EVENTS
        
        // LOAD COMMANDS
        getCommand("testing").setExecutor(new LobbyCommands());
        
        debug("&aPlugin loaded sucessfully!");
    }

    public void onDisable() {
        if (this.getConfig().getBoolean("server.socket-connector.enable")) {
            server.closeServerSocket();
        }
    }

    /*
      GETTING INSTANCE
     */
    public static SWL get() {
        return ins;
    }
    
    public static void debug(String str) {
        Bukkit.getConsoleSender().sendMessage(Tools.get().Text("&7[J3SWLobby]: " + str));
    }
    
    private void socketStart(Integer port) {
        try {
            this.startServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(SWL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startServerSocket(Integer port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        server = new SocketServer(serverSocket);
        server.start();
        
        SWL.debug("&aSocket Server started successfully!");
    }

	public ArenaManager getAM() {
		return am;
	}
    
}
