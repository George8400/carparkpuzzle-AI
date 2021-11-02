package graphic;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);  //Restituisce una sottoimmagine definita da una regione rettangolare specificata. Ad esempio, se abbiamo una tilemap, andiamo a selezionare la regione di immagine che ci interessa.
	     
		return img;
	}

}
