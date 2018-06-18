package Server;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Server extends JFrame implements ActionListener,Runnable {
    private JTextArea taMsg=new JTextArea("以下是聊天记录\n");
    private JTextField tfMsg=new JTextField("请您输入信息");
    private JButton btSend=new JButton("发送");
    private Socket s=null;
    public Server() {
        this.setTitle("服务器端");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(taMsg,BorderLayout.CENTER);
        tfMsg.setBackground(Color.yellow);
        this.add(tfMsg,BorderLayout.NORTH);
        this.add(btSend,BorderLayout.SOUTH);
        btSend.addActionListener(this);
        this.setSize(200,300);
        this.setVisible(true);
        try {
            ServerSocket ss=new ServerSocket(9999);
            s=ss.accept();
            new Thread(this).start();
        }
        catch (Exception ex) {
        }
    }

    public void run(){
        try {
            while(true) {
                InputStream is=s.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String str=br.readLine();   //读
                taMsg.append(str+"\n");     //添加内容
            }
        }
        catch (Exception ex) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            OutputStream os=s.getOutputStream();
            PrintStream ps=new PrintStream(os);
            ps.println("服务器说："+tfMsg.getText());
        }
        catch (Exception ex) {
        }
    }
    
    public static void main(String[] args) throws Exception {
        new Server();
    }
}