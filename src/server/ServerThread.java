/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
public class ServerThread implements Runnable {

	private Socket socketOfServer;
	private int clientNumber;
	private BufferedReader is;
	private BufferedWriter os;
	private boolean isClosed;

	public BufferedReader getIs() {
		return is;
	}

	public BufferedWriter getOs() {
		return os;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public ServerThread(Socket socketOfServer, int clientNumber) {
		this.socketOfServer = socketOfServer;
		this.clientNumber = clientNumber;
		System.out.println("Server thread number " + clientNumber + " Started");
		isClosed = false;
	}

	@Override
	public void run() {
		try {
			// Mở luồng vào ra trên Socket tại Server.
			is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			System.out.println("Khời động luông mới thành công, ID là: " + clientNumber);
			write("get-id" + "," + this.clientNumber);
			Server.serverThreadBus.sendOnlineList();
//			Server.serverThreadBus
//					.mutilCastSend("global-message" + "," + "---Client " + this.clientNumber + " đã đăng nhập---");
			String message;
	        while (!isClosed) {
	            message = is.readLine();
	            if (message == null) {
	                break;
	            }
	            String[] messageSplit = message.split(",");
	            if (messageSplit[0].equals("send-to-global")) {
	                Server.serverThreadBus.boardCast(this.getClientNumber(),
	                        "global-message," + "Client " + messageSplit[2] + ": " + messageSplit[1]);
	            } else if (messageSplit[0].equals("send-to-person")) {
	                Server.serverThreadBus.sendMessageToPersion(Integer.parseInt(messageSplit[3]),
	                        "Client " + messageSplit[2] + " (to bạn): " + messageSplit[1]);
	            } else if (messageSplit[0].equals("send-file")) {
	                String fileName = messageSplit[1];
	                long fileSize = Long.parseLong(messageSplit[2]);
	                receiveFile(fileName, fileSize);
	            }
	        }
	    } catch (IOException e) {
	        isClosed = true;
	        Server.serverThreadBus.remove(clientNumber);
	        System.out.println(this.clientNumber + " đã thoát");
	        Server.serverThreadBus.sendOnlineList();
	        Server.serverThreadBus.mutilCastSend("global-message," + "---Client " + this.clientNumber + " đã thoát---");
	    }
	}

	private void receiveFile(String fileName, long fileSize) {
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