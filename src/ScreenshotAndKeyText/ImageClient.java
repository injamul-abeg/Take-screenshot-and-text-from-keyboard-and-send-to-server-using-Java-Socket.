package ScreenshotAndKeyText;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.Socket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageClient {

	public static void main(String[] args) throws IOException {
		// Create a socket to connect to the server
        Socket socket = new Socket("127.0.0.1", 55286);
        System.out.println("Connected to server.");
        JFrame jFrame = new JFrame("Image Client");
        jFrame.setSize(408, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Read the image file
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\abeg\\eclipse-workspace\\ScreenshotAndText\\screenshot.png");
        JLabel JLabelPic = new JLabel(imageIcon);
        JButton jButton = new JButton("Send Image to Server.");
        jFrame.add(JLabelPic);
        jFrame.add(jButton);
        jFrame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                	// Create a BufferedReader to read the image from the server
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    Image image = imageIcon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image, 0, 0, null);
                    graphics.dispose();
                    ImageIO.write(bufferedImage, "png", bufferedOutputStream);
                    
                    // Close the streams and socket
                    bufferedOutputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}