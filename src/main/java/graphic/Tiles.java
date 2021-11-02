package graphic;

import java.awt.image.BufferedImage;


/*---------------
 Classe che gestisce la strutture dei livelli;
 ----------------*/
public class Tiles 
{
	SpriteSheet bs, ps, es;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage enemy_sheet = null;
	
	public BufferedImage cocaine = null;
	
	public BufferedImage[] block = new BufferedImage[2];   //array di immagini di 'Block'
	public BufferedImage[] player = new BufferedImage[20];  //array di immagini di 'Player'
	public BufferedImage[] enemy = new BufferedImage[16];   //array di immagini di 'Enemy'
	
	public Tiles()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			block_sheet = loader.loadImage("/Spritesheet/block_shee_1t.png");
			player_sheet = loader.loadImage("/Spritesheet/SpritesheetPlayer.png");
			enemy_sheet = loader.loadImage("/Spritesheet/SpritesheetEnemyPolice.png");
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		es = new SpriteSheet(enemy_sheet);
		
		getTiles();
	}

	private void getTiles() 
	{
		//leggi immagini blocchi
		block[0] = bs.grabImage(1, 1, 32, 32);  //dirt block; andiamo a memorizzare in block[0] la porzione di immagine che ci interessa
		block[1] = bs.grabImage(2, 1, 32, 32);  //grass block;
		
		//Player walk-right
		player[0] = ps.grabImage(1, 4, 40, 64);   //idle frame for player
		player[1] = ps.grabImage(2, 4, 40, 64);
		player[2] = ps.grabImage(3, 4, 40, 64);
		player[3] = ps.grabImage(4, 4, 40, 64);
		player[4] = ps.grabImage(5, 4, 40, 64);
		player[5] = ps.grabImage(6, 4, 40, 64);
		player[6] = ps.grabImage(7, 4, 40, 64);
		player[7] = ps.grabImage(8, 4, 40, 64);
		player[8] = ps.grabImage(9, 4, 40, 64);
		player[9] = ps.grabImage(10, 4, 40, 64);
		
		//Player walk-left
		player[10] = ps.grabImage(1, 3, 40, 64);   //idle frame for player
		player[11] = ps.grabImage(2, 3, 40, 64);
		player[12] = ps.grabImage(3, 3, 40, 64);
		player[13] = ps.grabImage(4, 3, 40, 64);
		player[14] = ps.grabImage(5, 3, 40, 64);
		player[15] = ps.grabImage(6, 3, 40, 64);
		player[16] = ps.grabImage(7, 3, 40, 64);
		player[17] = ps.grabImage(8, 3, 40, 64);
		player[18] = ps.grabImage(9, 3, 40, 64);
		player[19] = ps.grabImage(10, 3, 40, 64);
		
		
		
		
		//Enemy walk-right
		
		enemy[0] = es.grabImage(1, 1, 100, 176);
		enemy[1] = es.grabImage(2, 1, 100, 176);
		enemy[2] = es.grabImage(3, 1, 100, 176);
		enemy[3] = es.grabImage(4, 1, 100, 176);
		enemy[4] = es.grabImage(5, 1, 100, 176);
		enemy[5] = es.grabImage(6, 1, 100, 176);
		enemy[6] = es.grabImage(7, 1, 100, 176);
		enemy[7] = es.grabImage(8, 1, 100, 176);
		
		//Enemy walk-left
		enemy[15] = es.grabImage(1, 2, 100, 176);
		enemy[14] = es.grabImage(2, 2, 100, 176);
		enemy[13] = es.grabImage(3, 2, 100, 176);
		enemy[12] = es.grabImage(4, 2, 100, 176);
		enemy[11] = es.grabImage(5, 2, 100, 176);
		enemy[10] = es.grabImage(6, 2, 100, 176);
		enemy[9] = es.grabImage(7, 2, 100, 176);
		enemy[8] = es.grabImage(8, 2, 100, 176);
		
		
	
	}
}

