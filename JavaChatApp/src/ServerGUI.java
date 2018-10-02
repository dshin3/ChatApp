import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.zip.GZIPOutputStream;
import javax.swing.SwingUtilities;

public class ServerGUI extends JFrame implements ActionListener {

    public JList online;
    private JTextField ipaddress, textMessage;
    private JButton send, start, disconnect;
    private JTextArea chatArea;
    private JLabel port;
    int client[] = new int[100];
    private ObjectOutputStream out[] = new ObjectOutputStream[client.length+ 1];
    private ObjectInputStream in[] = new ObjectInputStream[client.length+ 1];
    String username[] = new String[client.length+1];
    static String b;
    public String nm, usm;
    private ServerSocket server;
    private Socket connect;
    boolean success = true;
    int id=0;
   
    ArrayList<String> UserList = new ArrayList<String>();
   

    public ServerGUI() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.setPreferredSize(new Dimension(650, 500));


        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.LIGHT_GRAY);
        p.add(port = new JLabel("Port No"));
        p.add(ipaddress = new JTextField("1500"));
        p.add(start = new JButton("START"));
        p.add(disconnect = new JButton("DISCONNECT"));
        disconnect.setEnabled(false);
        start.setBorderPainted(false);
        start.setBackground(Color.blue);
        start.setForeground(Color.WHITE);
        disconnect.setBorderPainted(false);
        disconnect.setBackground(Color.blue);
        disconnect.setForeground(Color.WHITE);
        ipaddress.setCaretPosition(0);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBackground(Color.LIGHT_GRAY);
        p1.add(chatArea = new JTextArea());

        chatArea.setPreferredSize(new Dimension(300, 350));
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.setBackground(Color.LIGHT_GRAY);
        p2.add(textMessage = new JTextField(20));

        p2.add(send = new JButton("SEND"));
        send.setBackground(Color.blue);
        send.setForeground(Color.WHITE);
        send.setBorderPainted(false);

        start.addActionListener(this);
        send.addActionListener(this);


        c.add(p, BorderLayout.NORTH);
        c.add(p1, BorderLayout.CENTER);
        c.add(p2, BorderLayout.SOUTH);


    }
    //current time
    SimpleDateFormat log = new SimpleDateFormat("HH:mm");
    String d = log.format(new Date());

    //Start server
    public void Start() {

        int portNo = 0;
        try {
            
            String no = ipaddress.getText();
            portNo = Integer.parseInt(no);
            chatArea.append("Connection to port " + portNo + "...\n");
            server = new ServerSocket(portNo);
            success = true;

        } catch (Exception ex) {
            chatArea.append("Error cannot bind to port \n");
            success = false;
        }

        if (success == true) {
            addClient ob1 = new addClient("RunServer");
            start.setEnabled(false);
            disconnect.setEnabled(true);
        }
    }

    public class addClient implements Runnable {

        Thread t;

        addClient(String tot) {
            t = new Thread(this, tot);
            t.start();
        }

        public void run() {
            while (true) {
                try {
                    try {
                        WaitClient();
                    } catch (Exception ex) {
                        break;
                    }
                    
                    for (int i = 0; i < client.length; i++) {
                        if (client[i] == 0) {
                            client[i] = i + 1;
                            id = i;
                            break;
                        }
                    }
                  
                  
                    //set stream to send and receive data
                    out[client[id]] = new ObjectOutputStream(connect.getOutputStream());
                    out[client[id]].flush();
                    in[client[id]] = new ObjectInputStream(connect.getInputStream());
                    chatArea.append(d + " Client:[" + client[id] + "] : Connected successful \n");
                   
                    //inform user that connection is successfull                    
                    sendUser(client[id], "Connected to the server");
                    
                    ChatMessage cm = (ChatMessage) in[client[id]].readObject();
                    handle(client[id], cm);
                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    
    public synchronized void handle(int num, ChatMessage cm){
        
        if(cm.type.equals("login")){
             username[client[findClient(num)]] = cm.sender;             
             send(client[findClient(num)], new ChatMessage("login", "SERVER", "TRUE", cm.sender));
             Announce("newuser", "SERVER", cm.sender);
             SendUserList(cm.sender);
        
        }else if(cm.type.equals("message")){
                if(cm.recipient.equals("All")){
                Announce("message", cm.sender, cm.content);
        }
                else{
                    send(findUserThread(cm.recipient), new ChatMessage(cm.type, cm.sender, cm.content, cm.recipient));
                    send(client[findClient(num)], new ChatMessage(cm.type, cm.sender, cm.content, cm.recipient));
                }
            }
    
    
    
    }
    
      public void SendUserList(String toWhom){
        for(int i = 0; i < id; i++){
            send(findUserThread(toWhom), new ChatMessage("newuser", "SERVER", username[client[i]], toWhom));
        }
    }
    
    public int findUserThread(String usr){
        for(int i = 0; i < id; i++){
            
            if(username[client[i]].equals(usr)){
               
                return client[i];
          
        }
        
        }
        
        return id;
    }
     private int findClient(int n){  
    	for (int i = 0; i < id; i++){
        	if (client[i] == n){
                   return client[i];
                   
                }
               
	}
	return id;
    }
    
   
                            
  
//wait for connection, then display connection information
    private void WaitClient() throws IOException {

        chatArea.append(d + " : Waiting for connection... \n");
        connect = server.accept();

        chatArea.append(d + " : Now connected to " + connect.getInetAddress().getHostName() + "\n");
    }

    //send message to specific user
    public void send(int number, ChatMessage cm) {

        try {
            out[number].writeObject(cm);
            out[number].flush();
        } catch (Exception e) {
        }

    }
    
    public void sendUser(int number, String info) {

        try {
            out[number].writeObject(info);
            out[number].flush();
        } catch (Exception e) {
        }

    }
    
     public void Announce(String type, String sender, String content){
        ChatMessage cm = new ChatMessage(type, sender, content, "All");
        for(int i = 0; i < id; i++){
            send(client[i], cm);
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
         

        } else if (e.getSource() == start) {
            Start();
        }
        if (e.getSource() == disconnect) {

       
        }
    }
}
