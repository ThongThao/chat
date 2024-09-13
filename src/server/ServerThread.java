package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;

/**
 * Handles client communication.
 */
public class ServerThread implements Runnable {

    private Socket socketOfServer;
    private String username;
    private BufferedReader is;
    private BufferedWriter os;
    private boolean isClosed;

    public BufferedReader getIs() {
        return is;
    }

    public BufferedWriter getOs() {
        return os;
    }

    public String getUsername() {
        return username;
    }

    public ServerThread(Socket socketOfServer) {
        this.socketOfServer = socketOfServer;
        this.username = null; // Initially set to null until username is received
        System.out.println("Server thread started for: " + socketOfServer);
        isClosed = false;
    }

    @Override
    public void run() {
        try {
            // Open input and output streams on the socket
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            
            // Read the username from the client
            String initialMessage = is.readLine();
            if (initialMessage != null && initialMessage.startsWith("username:")) {
                username = initialMessage.substring("username:".length());
                System.out.println("Client username received: " + username);
                
                // Notify all clients about the new user
                Server.serverThreadBus.sendOnlineList();
            } else {
                System.out.println("Failed to receive username from client.");
                return;
            }

            // Notify client of successful connection
            write("get-id,"+ this.username);
            
            String message;
            while (!isClosed) {
                message = is.readLine();
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
//             System.out.println("messageSplit[0]"+ messageSplit[0]+" " +messageSplit[2] + ": " + messageSplit[1]);
                if (messageSplit[0].equals("send-to-global")) {
                    Server.serverThreadBus.boardCast(this.getUsername(),
                            "global-message,"  + messageSplit[2] + ": " + messageSplit[1]);
                } else if (messageSplit[0].equals("send-to-person")) {
                    Server.serverThreadBus.sendMessageToPersion(messageSplit[3],
                            messageSplit[2] + " (tới bạn): " + messageSplit[1]);
                } else if (messageSplit[0].equals("send-file")) {
                    String fileName = messageSplit[1];
                    long fileSize = Long.parseLong(messageSplit[2]);
                    receiveFile(fileName, fileSize);
                }
            }
        } catch (IOException e) {
            isClosed = true;
            Server.serverThreadBus.remove(username);
            System.out.println(this.username + " đã thoát");
            Server.serverThreadBus.sendOnlineList();
            Server.serverThreadBus.mutilCastSend("global-message," + "---Client " + this.username + " đã thoát---");
        }
    }
    
//
    

    private void receiveFile(String fileName, long fileSize) {
        File directory = new File("received_files");
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu nó không tồn tại
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(Paths.get("received_files", fileName).toFile())) {
            InputStream is = socketOfServer.getInputStream();
            byte[] buffer = new byte[4096];
            long bytesRead = 0;
            int read;
            while ((read = is.read(buffer)) != -1 && bytesRead < fileSize) {
                fileOutputStream.write(buffer, 0, read);
                bytesRead += read;
            }
            fileOutputStream.flush();
            System.out.println("File received: " + fileName);

            // Gửi thông báo cho client về việc file đã được nhận
            PrintWriter out = new PrintWriter(socketOfServer.getOutputStream(), true);
            out.println("receive-file," + fileName + "," + fileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void write(String message) throws IOException {
        os.write(message);
        os.newLine();
        os.flush();
    }
}
