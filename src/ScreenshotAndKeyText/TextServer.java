package ScreenshotAndKeyText;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextServer {
    public static void main(String[] args) throws IOException {
        // Create a new JFrame
        JFrame frame = new JFrame("Text Server");

        // Set the size of the window
        frame.setSize(400, 400);

        // Set the layout manager for the frame
        frame.setLayout(new BorderLayout());

        // Specify what should happen when the user closes the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabelTxt = new JLabel("Waiting for text from client...");
        frame.add(jLabelTxt, BorderLayout.SOUTH);
        // Create a new JPanel and add it to the frame
        JPanel panel = new JPanel();
        frame.add(panel);

        // Set the layout manager for the panel
        panel.setLayout(new BorderLayout());

        // Create a new JTextArea and add it to the panel
        JTextArea textArea = new JTextArea();
        panel.add(textArea);

        // Make the text area not editable
        textArea.setEditable(false);

        // Set the text area to wrap lines and words
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        frame.setVisible(true);


        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started");

        // Listen for a connection request
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");


        // Create a BufferedReader to read the file
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\abeg\\eclipse-workspace\\ScreenshotAndText\\text.txt"));

        // Create a PrintWriter to send the contents of the file to the client
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read the file and send its contents to the client
        String line;
        while ((line = in .readLine()) != null) {
            out.println(line);
            textArea.setText(line);
        }
        JLabel jLabelPic = new JLabel();
        jLabelTxt.setText("Text received.");
        frame.add(jLabelPic, BorderLayout.CENTER);
        // Close the streams and socket
        in .close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}