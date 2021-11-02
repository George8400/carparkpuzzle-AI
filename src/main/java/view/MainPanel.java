package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.AI;
import core.Game;
import manager.Manager;
import model.Matrix;

public class MainPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	protected int WIDTH, HEIGHT;

	private JButton playAI;

	private JButton play;
	
	public MainPanel(Game game) {
		init(game);
	}

	private void init(Game game) {
		playAI = new JButton("PlayAI");
		play = new JButton("Gioca");
		
		WIDTH = 900;
		HEIGHT = 720;
		
		game.setBounds(150, 20, 600, 600);
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		
		playAI.setPreferredSize(new Dimension(120, 60));
		playAI.setBounds(WIDTH/3 - 60, HEIGHT - 80, 120, 60);
		playAI.setOpaque(false);
		
		play.setPreferredSize(new Dimension(120, 60));
		play.setBounds(WIDTH/2 + playAI.getWidth(), HEIGHT - 80, 120, 60);
		play.setOpaque(false);
		
		
		playAI.addActionListener(this);
		play.addActionListener(this);
		
		this.add(game);
		this.add(playAI);
		this.add(play);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(playAI == (JButton) e.getSource()) {
			System.out.println("Button - playAI");
			Manager.ai.playAI();
		}
		if(play == (JButton) e.getSource()) {
			System.out.println("Button - Gioca");
			Manager.infoObjects();
			Manager.type = null;
			Matrix.isWin();
		}
	}
	

}

