package events.hellows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.tyrus.server.Server;
 
public class WebSocketServer {
	private static final Logger LOGGER = Grizzly.logger(Server.class);
 
    public static void main(String[] args) {
        runServer();
    } 
 
    public static void runServer() {
        Server server = new Server("localhost", 8025, "/websockets", new HashMap<String,Object>(), WordgameServerEndpoint.class);
 
        try {
            server.start();
			LOGGER.info("Press 's' to shutdown now the server...");
			while(true){
				int c = System.in.read();
				if (c == 's')
					break;
			}
        } catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            server.stop();
			LOGGER.info("Server stopped");
        }
    }
}
