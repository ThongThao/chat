package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.JTextComponent;

import java.awt.Color;

/**
 *
 * @author Admin
 */
public class Client extends javax.swing.JFrame {

	private Thread thread;
	private BufferedWriter os;
	private BufferedReader is;
	private Socket socketOfClient;
	private List<String> onlineList;
	private String serverIP;
	 private String username;

	public Client() {
		initComponents(); // Must be called first
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		jTextArea1.setEditable(false);
		jTextArea2_1.setEditable(false);
		onlineList = new ArrayList<>();
		serverIP = JOptionPane.showInputDialog("Nhập IP của Server:");
		if (serverIP != null && !serverIP.trim().isEmpty()) {
	        this.username = JOptionPane.showInputDialog("Nhập username của bạn:");
	        if (this.username != null && !this.username.trim().isEmpty()) {
	            setUpSocket(serverIP, this.username);
	        } else {
	            JOptionPane.showMessageDialog(rootPane, "Username không hợp lệ");
	            System.exit(1);
	        }
	    } else {
	        JOptionPane.showMessageDialog(rootPane, "IP Server không hợp lệ");
	        System.exit(1);
	    }
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
	    jPanel3 = new javax.swing.JPanel();
	    jTabbedPane1 = new javax.swing.JTabbedPane();
	    jPanel1 = new javax.swing.JPanel();
	    jPanel2 = new javax.swing.JPanel();
	    jScrollPane1 = new javax.swing.JScrollPane();
	    jTextArea1 = new javax.swing.JTextArea();
	    jTextField1 = new javax.swing.JTextField();
	    jButton1 = new javax.swing.JButton();
	    JButton jButtonSendFile = new JButton("Send File");
	    jLabel1 = new javax.swing.JLabel();
	    jLabel2 = new javax.swing.JLabel();
	    jLabel3 = new javax.swing.JLabel();

	    btnNewButton_1 = new JButton(new ImageIcon(Client.class.getResource("/Img/smile.png")));
	    btnNewButton_1_1 = new JButton(new ImageIcon(Client.class.getResource("/Img/angry.png")));
	    btnNewButton_1_2 = new JButton(new ImageIcon(Client.class.getResource("/Img/love.png")));

	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jButton1.setIcon(null);
        JButton jButton2 = new javax.swing.JButton();
        jButton2.setIcon(null);
        jComboBox1_1 = new javax.swing.JComboBox<>();
        jComboBox1_1.setBackground(new Color(238, 255, 255));
        jLabel1_1 = new javax.swing.JLabel();
        jLabel2_1 = new javax.swing.JLabel();
        jLabel3_1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


    

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendIcon("😂");
            }
        });

        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendIcon("😍");
            }
        });

        btnNewButton_1_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendIcon("😡");
            }
        });
        jButton1.setText("Gửi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButtonSendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendFile();
            }
        });
        

        jComboBox1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1_1.setText("Chọn người nhận");

		
	
        jLabel3_1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3_1.setText("{Người nhận}");
        
        JButton btnNewButton = new JButton("File");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  sendFile();
        	}
        });
        
        jScrollPane2_1 = new JScrollPane();
        
        JLabel jLabel1_1_1 = new JLabel();
        jLabel1_1_1.setText("Danh sách online");
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel1_1_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jScrollPane2_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGap(10)
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addComponent(jLabel3_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
        							.addGap(45))
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        								.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        								.addComponent(jLabel1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jComboBox1_1, Alignment.LEADING, 0, 335, Short.MAX_VALUE)
        								.addComponent(jTextField1, Alignment.LEADING, 339, 339, 339)
        								.addGroup(Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
        									.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
        							.addContainerGap())
        						.addComponent(jLabel2_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGap(46)
        					.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1_1)
        				.addComponent(jLabel1_1_1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane2_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(jComboBox1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jLabel3_1)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel2_1)
        						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        					.addGap(38))))
        );
        
        jTextArea2_1 = new JTextArea();
        jTextArea2_1.setRows(5);
        jTextArea2_1.setColumns(20);
        jScrollPane2_1.setViewportView(jTextArea2_1);
        jPanel2.setLayout(jPanel2Layout);

        jTabbedPane1.addTab("Nhắn tin", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	
	private void jButton1ActionPerformed(ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		String messageContent = jTextField1.getText();
		if (messageContent.isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tin nhắn");
			return;
		}
		if (jComboBox1_1.getSelectedIndex() == 0) {
			try {
				write("send-to-global" + "," + messageContent + "," + this.username);
				jTextArea1.setText(jTextArea1.getText() + "Bạn: " + messageContent + "\n");
				jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
			}
		} else {
			try {
				 String partner = (String) jComboBox1_1.getSelectedItem();
				write("send-to-person" + "," + messageContent + "," + this.username + "," + partner);
				jTextArea1.setText(
						jTextArea1.getText() + "Bạn (tới " + partner + "): " + messageContent + "\n");
				jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
			}
		}
		jTextField1.setText("");
	}// GEN-LAST:event_jButton1ActionPerformed
	private void jComboBox1ActionPerformed(ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
		if (jComboBox1_1.getSelectedIndex() == 0) {
			jLabel3_1.setText("Global");
		} else {
			jLabel3_1.setText("Đang nhắn với " + jComboBox1_1.getSelectedItem());
		}
	}// GEN-LAST:event_jComboBox1ActionPerformed

	private void setUpSocket(String serverIP , String username) {
		try {
			thread = new Thread() {
				@Override
				public void run() {
					try {
						// Gửi yêu cầu kết nối tới Server.
						socketOfClient = new Socket(serverIP, 7777);
						System.out.println("Kết nối thành công!");
						os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
						is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
						 // Gửi username đến server
	                    write("username:" + username);
						String message;
						while (true) {
							message = is.readLine();
							if (message == null) {
								break;
							}
							String[] messageSplit = message.split(",");
							if (messageSplit[0].equals("get-id")) {
								setUsername(messageSplit[1]);
								setIDTitle();
							}
							if (messageSplit[0].equals("update-online-list")) {
								onlineList = new ArrayList<>();
								String online = "";
								String[] onlineSplit = messageSplit[1].split("-");
								for (int i = 0; i < onlineSplit.length; i++) {
									onlineList.add(onlineSplit[i]);
									online +=  onlineSplit[i] + " đang online\n";
								}
								jTextArea2_1.setText(online);
								updateCombobox();
							}
							if (messageSplit[0].equals("global-message")) {
								jTextArea1.setText(jTextArea1.getText() + messageSplit[1] + "\n");
								jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
							}
							if (messageSplit[0].equals("receive-file")) {
								 receiveFile(messageSplit[1], Long.parseLong(messageSplit[2]));
							}
							
						}
					} catch (UnknownHostException e) {
						JOptionPane.showMessageDialog(rootPane, "Không thể kết nối đến server: " + e.getMessage());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối: " + e.getMessage());
					}
				}
			};
			thread.start();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Lỗi: " + e.getMessage());
		}
	}

	private void updateCombobox() {
		jComboBox1_1.removeAllItems();
		jComboBox1_1.addItem("Gửi tất cả");
		String idString = this.username;
		for (String e : onlineList) {
			if (!e.equals(idString)) {
				jComboBox1_1.addItem(e);
			}
		}
	}
	
	private void sendIcon(String icon) {
        try {
            if (jComboBox1_1.getSelectedIndex() == 0) {
                write("send-to-global," + icon + "," + this.username);
                jTextArea1.setText(jTextArea1.getText() + "Bạn: " + icon + "\n");
            } else {
                String partner = (String) jComboBox1_1.getSelectedItem();
                write("send-to-person," + icon + "," + this.username + "," + partner);
                jTextArea1.setText(jTextArea1.getText() + "Bạn (tới " + partner + "): " + icon + "\n");
            }
            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra khi gửi icon");
        }
    }

	private void sendFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    int returnValue = fileChooser.showOpenDialog(this);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File file = fileChooser.getSelectedFile();
	        String fileName = file.getName();
	        long fileSize = file.length();

	        try {
	            // Send file details to server
	            write("send-file," + fileName + "," + fileSize + "," + this.username);

	            // Send file data
	            try (FileInputStream fileInputStream = new FileInputStream(file)) {
	                byte[] buffer = new byte[4096];
	                int bytesRead;
	                OutputStream os = socketOfClient.getOutputStream();
	                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	                    os.write(buffer, 0, bytesRead);
	                }
	                os.flush();
	                // Hiển thị file đã gửi trên giao diện
	                jTextArea1.setText(jTextArea1.getText() + "Bạn đã gửi file: " + fileName + "\n");
	                jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
	            }
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(rootPane, "Error sending file: " + e.getMessage());
	        }
	    }
	}

	private void receiveFile(String fileName, long fileSize) {
	    File directory = new File("received_files");
	    if (!directory.exists()) {
	        directory.mkdirs(); // Tạo thư mục nếu nó không tồn tại
	    }
	    try (FileOutputStream fileOutputStream = new FileOutputStream(new File(directory, fileName))) {
	        InputStream is = socketOfClient.getInputStream();
	        byte[] buffer = new byte[4096];
	        long bytesRead = 0;
	        int read;
	        while ((read = is.read(buffer)) != -1 && bytesRead < fileSize) {
	            fileOutputStream.write(buffer, 0, read);
	            bytesRead += read;
	        }
	        fileOutputStream.flush();
	        System.out.println("File received: " + fileName);
	        // Hiển thị thông báo về file đã nhận
	        jTextArea1.setText(jTextArea1.getText() + "Nhận file: " + fileName + "\n");
	        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(rootPane, "Lỗi khi nhận file: " + e.getMessage());
	    }
	}
	private void setIDTitle() {
		this.setTitle(this.username);
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private void write(String message) throws IOException {
		os.write(message);
		os.newLine();
		os.flush();
	}
	

	 public static void main(String args[]) {
	        new Client();
	    }

	    private JButton jButton1;
	    private JComboBox<String> jComboBox1;
	    private JComboBox<String> jComboBox1_1;
	    private JLabel jLabel1;
	    private JLabel jLabel1_1;
	    private JLabel jLabel2;
	    private JLabel jLabel2_1;
	    private JLabel jLabel3;
	    private JLabel jLabel3_1;
	    private JPanel jPanel1;
	    private JPanel jPanel2;
	    private JPanel jPanel3;
	    private JScrollPane jScrollPane1;
	    private JTabbedPane jTabbedPane1;
	    private JTextArea jTextArea1;
	    private JTextField jTextField1;

	private JButton      btnNewButton_1 ;
	private JButton      btnNewButton_1_1;
	private JButton     btnNewButton_1_2;
	private JScrollPane jScrollPane2_1;
	private JTextArea jTextArea2_1;
}
