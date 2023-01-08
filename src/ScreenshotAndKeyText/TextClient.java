package ScreenshotAndKeyText;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TextClient {
    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame("Text Client");
        jFrame.setSize(408, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton jButtontxt = new JButton("Send text to Server.");
        //jFrame.add(JLabelPic);
        jFrame.add(jButtontxt);
        jFrame.setVisible(true);

        // Create a socket to connect to the server
        Socket socket = new Socket("localhost", 8000);
        jButtontxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    // Create a BufferedReader to read the contents of the file from the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Read the contents of the file from the server and print them to the console
                    String line;
                    while ((line = in .readLine()) != null) {
                        System.out.println(line);
                    }

                    // Close the streams and socket
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}