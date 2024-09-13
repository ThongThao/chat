package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main server class to accept client connections.
 */
public class Server {

    public static volatile ServerThreadBus serverThreadBus;
    public static Socket socketOfServer;

    public static void main(String[] args) {
        ServerSocket listener = null;
        serverThreadBus = new ServerThreadBus();
        System.out.println("Server is waiting to accept user...");

        // Open a ServerSocket at port 7777
        try {
            listener = new ServerSocket(7777);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        //
        // Create a ThreadPoolExecutor to handle multiple threads efficiently
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, // corePoolSize
                100, // maximumPoolSize
                10, // thread timeout
                TimeUnit.SECONDS, 
                new ArrayBlockingQueue<>(8) // queueCapacity
        );

        try {
            while (true) {
                // Accept a connection from a client and create a new Socket object.
                socketOfServer = listener.accept();
                System.out.println("Accepted connection from: " + socketOfServer);

                // Start a new thread for the client connection
                ServerThread serverThread = new ServerThread(socketOfServer);
                serverThreadBus.add(serverThread);
                System.out.println("Number of active threads: " + serverThreadBus.getLength() + " " + socketOfServer);
                executor.execute(serverThread);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (listener != null) {
                    listener.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
