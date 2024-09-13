package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages communication between server threads.
 */
public class ServerThreadBus {
    private List<ServerThread> listServerThreads;

    public List<ServerThread> getListServerThreads() {
        return listServerThreads;
    }

    public ServerThreadBus() {
        listServerThreads = new ArrayList<>();
    }

    public synchronized void add(ServerThread serverThread) {
        listServerThreads.add(serverThread);
    }

    public synchronized void mutilCastSend(String message) { // like sockets.emit in socket.io
        for (ServerThread serverThread : listServerThreads) {
            try {
                serverThread.write(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public synchronized void boardCast(String username, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (!serverThread.getUsername().equals(username)) {
                try {
                    serverThread.write(message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public synchronized int getLength() {
        return listServerThreads.size();
    }
    //
    

    public synchronized void sendOnlineList() {
        StringBuilder res = new StringBuilder();
        for (ServerThread serverThread : listServerThreads) {
            res.append(serverThread.getUsername()).append("-");
        }
        // Remove the trailing dash
        if (res.length() > 0) {
            res.setLength(res.length() - 1);
        }
        mutilCastSend("update-online-list," + res.toString());
    }

    public synchronized void sendMessageToPersion(String username, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (serverThread.getUsername().equals(username)) {
                try {
//                    serverThread.write("private-message," + message);
                    serverThread.write("global-message" + "," + message);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public synchronized void remove(String username) {
        listServerThreads.removeIf(serverThread -> serverThread.getUsername().equals(username));
    }
}
