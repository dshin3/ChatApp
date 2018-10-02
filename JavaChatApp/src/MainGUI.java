import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MainGUI extends javax.swing.JFrame {
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    static String b; //variable for message
    private Socket join;
    boolean success = true;
    private String serverIP = "10.1.1.1"; //set IP Address
    ArrayList<String> userlist = new ArrayList<String>(); //ArrayList to store online users
    //current time
    SimpleDateFormat log = new SimpleDateFormat("HH:mm");
    String d = log.format(new Date());
    public File file;
    public String serverAddr, username, password;
    DefaultListModel model = new DefaultListModel(); 
     

    public MainGUI() {
        initComponents();
        
      
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        start = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textMessage = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        usernm = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        online = new javax.swing.JList();
        upload = new javax.swing.JButton();
        filename = new javax.swing.JTextField();
        stop = new javax.swing.JButton();
        login = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        portnumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passwordfield = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ICARE ");
        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(840, 650));
        setResizable(false);
        getContentPane().setLayout(null);

        jButton3.setText("Send");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(710, 110, 100, 30);

        start.setBackground(new java.awt.Color(51, 51, 255));
        start.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        start.setForeground(new java.awt.Color(255, 255, 255));
        start.setText("Start");
        start.setBorder(null);
        start.setBorderPainted(false);
        start.setRequestFocusEnabled(false);
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start);
        start.setBounds(20, 100, 90, 40);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 820, 0);
        getContentPane().add(textMessage);
        textMessage.setBounds(270, 450, 440, 70);

        chatArea.setColumns(20);
        chatArea.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chatArea.setRows(5);
        jScrollPane2.setViewportView(chatArea);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(270, 150, 540, 290);
        getContentPane().add(usernm);
        usernm.setBounds(540, 10, 170, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Enter your nickname :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 10, 160, 30);

        send.setBackground(new java.awt.Color(51, 51, 255));
        send.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        send.setForeground(new java.awt.Color(255, 255, 255));
        send.setText("Send");
        send.setBorder(null);
        send.setBorderPainted(false);
        send.setRequestFocusEnabled(false);
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        getContentPane().add(send);
        send.setBounds(720, 470, 90, 40);

        online.setBackground(new java.awt.Color(0, 0, 0));
        online.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        online.setForeground(new java.awt.Color(102, 255, 0));
        jScrollPane3.setViewportView(online);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(20, 150, 230, 370);

        upload.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        upload.setText("+");
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });
        getContentPane().add(upload);
        upload.setBounds(650, 110, 50, 30);
        getContentPane().add(filename);
        filename.setBounds(270, 110, 370, 30);

        stop.setBackground(new java.awt.Color(51, 51, 255));
        stop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stop.setForeground(new java.awt.Color(255, 255, 255));
        stop.setText("Log Out");
        stop.setToolTipText("");
        stop.setBorder(null);
        stop.setBorderPainted(false);
        stop.setRequestFocusEnabled(false);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        getContentPane().add(stop);
        stop.setBounds(720, 60, 90, 40);

        login.setBackground(new java.awt.Color(51, 51, 255));
        login.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Log In");
        login.setToolTipText("");
        login.setBorder(null);
        login.setBorderPainted(false);
        login.setRequestFocusEnabled(false);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        getContentPane().add(login);
        login.setBounds(720, 10, 90, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Port No      :");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 60, 100, 30);
        getContentPane().add(ip);
        ip.setBounds(110, 20, 150, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("IP Address :");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 20, 100, 30);
        getContentPane().add(portnumber);
        portnumber.setBounds(110, 60, 150, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Enter your password :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(380, 60, 160, 30);
        getContentPane().add(passwordfield);
        passwordfield.setBounds(540, 60, 170, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed

        Start();
        String group = "All";
        online.setModel(model);
        model.addElement(group);
        online.setSelectedIndex(0);
        send(new ChatMessage("test", "testUser", "testContent", "SERVER"));

       
    }//GEN-LAST:event_startActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        String z = textMessage.getText();
        String target = online.getSelectedValue().toString();
       
           
        if(!z.isEmpty() && !target.isEmpty()){
            textMessage.setText("");
            send(new ChatMessage("message", username, z, target));
        }

    }//GEN-LAST:event_sendActionPerformed

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        // TODO add your handling code here:
        //Create a file chooser
        //In response to a button click:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();

        if (file != null) {
            if (!file.getName().isEmpty()) {
                String str;

                if (filename.getText().length() > 30) {
                    String t = file.getPath();
                    str = t.substring(0, 20) + " [...] " + t.substring(t.length() - 20, t.length());
                } else {
                    str = file.getPath();
                }
                filename.setText(str);
            }
        }
    }//GEN-LAST:event_uploadActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        long size = file.length();
        if (size < 120 * 1024 * 1024) {
             send(new ChatMessage("upload_req", username, file.getName(), online.getSelectedValue().toString()));
          

        } else {
            chatArea.append("File is size too large\n");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        if (!username.isEmpty()) {
            send(new ChatMessage("logout", username, password, "SERVER"));
        }
    }//GEN-LAST:event_stopActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        username = usernm.getText();
        password = passwordfield.getText();
        
        if(!username.isEmpty() && !password.isEmpty()){
            send(new ChatMessage("login", username, password, "SERVER"));
        }
    }//GEN-LAST:event_loginActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea chatArea;
    public javax.swing.JTextField filename;
    public javax.swing.JTextField ip;
    public javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JButton login;
    public javax.swing.JList online;
    public javax.swing.JPasswordField passwordfield;
    public javax.swing.JTextField portnumber;
    public javax.swing.JButton send;
    public javax.swing.JButton start;
    public javax.swing.JButton stop;
    private javax.swing.JTextField textMessage;
    public javax.swing.JButton upload;
    public javax.swing.JTextField usernm;
    // End of variables declaration//GEN-END:variables

  //Start client program
    public void Start() {
        
        
        try {
            
            start.setEnabled(false);
            chatArea.append(d + " : Attempting connection... \n");
            
            join = new Socket(serverAddr, 10500);
            chatArea.append(d + " : Connected to - " + join.getInetAddress().getHostName() + "\n");
            
            
            success = true;

        } catch (Exception ex) {
            chatArea.append("Error cannot bind to port \n");            
            success = false;
        }

        if (success == true) {

            ClientThread ct = new ClientThread();
            

        }
    }

    class ClientThread implements Runnable {

        ClientThread ct;
        Thread t;

        ClientThread() {
            t = new Thread(this, "RunClient");
            t.start();
        }

        public void run() {
            try {
                try {
                    out = new ObjectOutputStream(join.getOutputStream());
                    out.flush();
                    in = new ObjectInputStream(join.getInputStream());
                    b = (String) in.readObject();
                      
                    chatArea.append(b + "\n");
                    chatArea.setCaretPosition(chatArea.getText().length());
                                    
                } catch (Exception e) {   }

                CThread c1 = new CThread();


            } catch (Exception ex) {   }
        }
    }

    class CThread implements Runnable {

        CThread ob1;
        Thread t;

        CThread() {
            t = new Thread(this, "Message");
            t.start();
        }

        public void run() {
            try {
                do {
                    try {
                       ChatMessage cm = (ChatMessage) in.readObject();
                       System.out.println("Incoming : "+cm.toString());
                       if(cm.type.equals("login")){
                            if(cm.content.equals("TRUE")){
                            chatArea.append("[SERVER > Me] : Login Successful\n"); 
                            } else{
                            chatArea.append("[SERVER > Me] : Login Failed\n");
                            }
                    } else if(cm.type.equals("test")){
                         start.setEnabled(false);
                    }else if(cm.type.equals("message")){
                    if(cm.recipient.equals(username)){
                        chatArea.append("["+cm.sender +" > Me] : " + cm.content + "\n");
                    }
                    else{
                        chatArea.append("["+ cm.sender +" > "+ cm.recipient +"] : " + cm.content + "\n");
                    } 
                    }else if(cm.type.equals("newuser")){
                    
                            online.setModel(model);
                            model.addElement(cm.content); 
                        
                    }else if(cm.type.equals("upload_req")){
                    
                                            
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(cm.content));
                        int returnVal = jf.showSaveDialog(this);
                       
                        String saveTo = jf.getSelectedFile().getPath();
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            Download dwn = new Download(saveTo);
                            Thread t = new Thread(dwn);
                            t.start();
                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
                            send(new ChatMessage("upload_res", username, (""+dwn.port), cm.sender));
                        }
                        else{
                            send(new ChatMessage("upload_res", username, "NO", cm.sender));
                        }
                    }
                    else if(cm.type.equals("upload_res")){
                    if(!cm.content.equals("NO")){
                        int port  = Integer.parseInt(cm.content);
                        String addr = cm.sender;
                        
                    
                        Upload upl = new Upload(serverAddr, port, file);
                        Thread t = new Thread(upl);
                        t.start();
                    }
                    else{
                        chatArea.append("[SERVER > Me] : "+cm.sender+" rejected file request\n");
                    }
                }
                else{
                    chatArea.append("[SERVER > Me] : Unknown message type\n");
    }
    } catch (Exception ex) {
                        chatArea.append("Server is disconnected" + "\n");
                        
                        try {
                            in.close();
                            out.close();
                            join.close();
                        } catch (Exception ex2) {      }
                    }
                } while (!b.equalsIgnoreCase("bye"));
            } catch (Exception ex) {
            }
        }
    }
    
  
    void send(ChatMessage cm) {
        try {
            out.writeObject(cm);
            out.flush();
        } catch (Exception e) {
        }
    }


}

