package ScreenshotAndKeyText;

import java.io.*;
public class TakeKeyboardText {

	// Take input from keyboard
	public void TakeKeyboardInput() throws IOException {
		DataInputStream s = new DataInputStream(System.in);
		FileOutputStream fout= new FileOutputStream("text.txt");
		
		System.out.println("Enter text (Type Enter to finish):");
		
		char ch;
		while((ch=(char)s.read())!='\n')
				fout.write(ch);
		
		fout.close();
	}
}
