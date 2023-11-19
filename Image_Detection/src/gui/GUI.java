package gui;

import javax.swing.JFrame;

public class GUI {
	
	public JFrame jf1;
	
	public GUI()
	{
		
		
		jf1 = new JFrame();
		jf1.setSize(1600, 1000);
		jf1.setTitle("Image Comparer");
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setLayout(null);
		jf1.setResizable(false);
		jf1.requestFocus();
		
		
		Draw lbldraw = new Draw();
		lbldraw.setBounds(0, 0, 1600, 1000);
		lbldraw.setVisible(true);
		jf1.add(lbldraw);
		
		jf1.setVisible(true);
		
		System.out.println("Initiation Complete");
	}
}
