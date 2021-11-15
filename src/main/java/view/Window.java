package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import core.Game;

public class Window extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	public Window() {
		
		Game game = new Game();
		
		SideBarPanel sidebar = new SideBarPanel();
		MainPanel mainpanel = new MainPanel(game);
		
		this.setFocusable(true);
		
		this.addKeyListener(this);
		
		this.setTitle("Car Puzzle Game AI");
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280, 780));
		
		this.getContentPane().add(sidebar, BorderLayout.WEST);
		this.getContentPane().add(mainpanel, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		game.start();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
