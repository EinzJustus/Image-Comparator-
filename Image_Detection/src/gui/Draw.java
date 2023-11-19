package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Draw extends JLabel{

	private BufferedImage img1;
	private BufferedImage img2;
	private BufferedImage img3;
	
	private File f = null;
	public Draw()
	{
		try {
			img1 = ImageIO.read(new File("res/img1.png"));
			img2 = ImageIO.read(new File("res/img2.png"));
			CompareImages();
			img3 = ImageIO.read(new File("res/output.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(new Color(80, 80, 80));
		g.fillRect(0, 0, 1600, 1000);		
			
		g.drawImage(img1, 0, 25, 800, 450, null);
		g.drawImage(img2, 800, 25, 800, 450, null);
		
		g.setColor(new Color(120, 235, 160));
		g.drawImage(img3, 400, 475, 800, 450, null);
		// g.fillRect(400, 475, 800, 450);
		System.out.println("Images Drawn");
		
	
	
	
		repaint();
		
	}
	
	public void CompareImages()
	{
		
		int width  = img1.getWidth();
		int height = img1.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int col = 0; col < width; col++)
		{
		  for (int row = 0; row < height; row++)
		  {
			 // image.setRGB(col, row, MakeGrey(new Color(img1.getRGB(col, row))).getRGB());
			  	image.setRGB(col, row, HighlightDifferences(new Color(img1.getRGB(col, row)), new Color(img2.getRGB(col, row))).getRGB());
		  }
		}    
		  
		try {
			f = new File("res/output.png");
			ImageIO.write(image, "png", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Color MakeGrey(Color c)
	{
		int tmp = 0;
		
		tmp += c.getRed();
		tmp += c.getGreen();
		tmp += c.getBlue();
		
		tmp = tmp/3;
		
		return new Color(tmp,tmp,tmp);
	}

	public Color HighlightDifferences(Color c1, Color c2)
	{
		Color c = Color.WHITE;
		
		int tmp = 0;
		tmp += Math.abs(c1.getBlue()-c2.getBlue()); 
		tmp += Math.abs(c1.getGreen()-c2.getGreen()); 
		tmp += Math.abs(c1.getRed()-c2.getRed());
		
		if(tmp < 1)
		{
			return c;
		}else if(tmp < 50)
		{
			c = new Color(255-tmp*5,0,0);
		}
		else
		{
			c = new Color(255-(tmp/3),0,0);
		}
		
		return c;
	}
}
