package ScreenshotAndKeyText;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ImageServer {

	public static void main(String[] args) throws IOException {

    	JFrame jFrame = new JFrame("Image Server");
    	jFrame.setSize(400, 400);
    	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JLabel jLabelText = new JLabel("Waiting for image from client...");
    	
    	jFrame.add(jLabelText, BorderLayout.SOUTH);
    	jFrame.setVisible(true);
    	
    	// Create a server socket
        ServerSocket serverSocket = new ServerSocket(55286);
        
        // Listen for a connection request
        Socket socket = serverSocket.accept();
        
        InputStream InputStream = socket.getInputStream();
        
        // Create a BufferedReader to read the image
        BufferedInputStream bufferedInputStream = new BufferedInputStream(InputStream);
        BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
        
        // Close the streams and socket
        bufferedInputStream.close();
        socket.close();

        JLabel jLabelPic = new JLabel(new ImageIcon(bufferedImage));
        jLabelText.setText("Image received.");
        jFrame.add(jLabelPic, BorderLayout.CENTER);
    }
}