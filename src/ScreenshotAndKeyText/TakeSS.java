package ScreenshotAndKeyText;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TakeSS {
    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();

        JButton sskey = new JButton("Take Screenshot & Keyboard Input");
        sskey.setBounds(60, 90, 250, 40);
        sskey.setBackground(Color.WHITE);
        sskey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the size of the screen
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Rectangle screenRectangle = new Rectangle(screenSize);

                    // Create a screen capture
                    Robot robot = new Robot();
                    BufferedImage image = robot.createScreenCapture(screenRectangle);

                    // Save the image to a local folder
                    String fileName = "screenshot.png";
                    File file = new File(fileName);
                    ImageIO.write(image, "png", file);

                    System.out.println("Screenshot saved to " + file.getAbsolutePath());

                    // Take input from keyboard
                    //Creating TakeKeyboardText class's object
                    TakeKeyboardText ktxt = new TakeKeyboardText();
                    ktxt.TakeKeyboardInput();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        

        f.add(sskey);
        f.setSize(400, 500);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
        f.setVisible(true);
    }
}