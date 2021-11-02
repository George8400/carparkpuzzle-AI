package core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphic.BufferedImageLoader;
import manager.Manager;
import model.Matrix;

public class Game extends Canvas implements Runnable, MouseListener{
	private static final long serialVersionUID = 1L;

	public static int WIDTH, HEIGHT;
	
	public static boolean running = false;
	public static boolean pause = false;
	
	private Thread thread;
	private AI ai;
	
	BufferedImageLoader loader;
	public static BufferedImage background = null;
	
	public static Manager manager;

	static final int cols = 6;
	static final int rows = 6;
	
	static final int originX = 100;
	static final int originY = 100;
	static final int cellSide = 100;
	
	
	/*-------------------------
	Inizializza oggetti gioco
	--------------------------*/
	private void init() {
		WIDTH = 600;
		HEIGHT = 600;
		
		loader = new BufferedImageLoader();
		background = loader.loadImage("/LevelBackground/background1.jpg");
		
		ai = new AI();
		
		manager = new Manager(ai);
		
		Matrix gameMatrix = Matrix.getInstance(manager);
		
		this.addMouseListener(this);
	
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		init();
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; // frame al secondo
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int updates = 0;
		@SuppressWarnings("unused")
		int frames = 0;

		while (running) {
			this.requestFocus();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (!pause)
					tick();
				updates++;
				delta--;

			}

			if (!pause)
				render();
			frames++;

			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
				updates = 0;
			}
		}
	}
	
	
	/*-----------
	 Logic Game
	 -----------*/

	private final void tick() 
	{
		manager.tick();
	}

	
	private final void render() 
	{

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics(); 
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		
		
		for(int i = 0; i < rows + 1; i++)
			g.drawLine(0, i * cellSide, cols * cellSide, i * cellSide);
		
		for(int i = 0; i < rows + 1; i++)
			g.drawLine(i * cellSide, 0, i * cellSide, rows * cellSide);
		
		manager.render(g2d);
		
		g2d.dispose();
		bs.show();
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		System.out.println(x + " " + y);
		
		if(Manager.type != null)
			manager.createObject(x, y, Manager.type);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
