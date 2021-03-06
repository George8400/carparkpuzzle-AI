package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import id.Type;
import manager.Manager;
import model.Matrix;

public class SideBarPanel extends JPanel implements ActionListener{

	public int WIDTH, HEIGHT;
	
	JButton l1;
	JButton l2;
	JButton l3;
	JButton l4;
	
	JButton carHorizontal;
	JButton carVertical;
	JButton camionHorizontal;
	JButton camionVertical;
	JButton player;
	
	JButton clearLevel;
	
	JButton exit;
	
	public SideBarPanel() {
		init();
	}
	
	
	private void init() {
		
		WIDTH = 380; 
		HEIGHT = 720;
		
		this.setLayout(null);
		
		this.setPreferredSize(new Dimension(380, 720));
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		
		setButtons();
	}
	
	
	private void setButtons() {
		
		int marginTop = 20;
		int widthButton = 150;
		int heightButton = 50;
		
		l1 = new JButton("L1");
		l2 = new JButton("L2");
		l3 = new JButton("L3");
		l4 = new JButton("L4");
		
		carHorizontal = new JButton("Car Horizontal");
		carVertical = new JButton("Car Vertical");
		camionHorizontal = new JButton("Camion Horizontal");
		camionVertical = new JButton("Camion Vertical");
		player = new JButton("Player");
		clearLevel = new JButton("Clear Level");
		exit = new JButton("Exit");
		
		l1.setBounds(40, 40, 60, 40);
		l2.setBounds(120, 40, 60, 40);
		l3.setBounds(200, 40, 60, 40);
		l4.setBounds(280, 40, 60, 40);
		
		carHorizontal.setBounds(WIDTH/2 - widthButton/2, HEIGHT/7 * 2, widthButton, heightButton);
		carVertical.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 3 + marginTop, widthButton, heightButton);
		camionHorizontal.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 4 + marginTop, widthButton, heightButton);
		camionVertical.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 5 + marginTop, widthButton, heightButton);
		player.setBounds(WIDTH/2 - widthButton/2, HEIGHT/7 + marginTop, widthButton, heightButton);
		
		clearLevel.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 6 + marginTop, widthButton, heightButton/2);
		exit.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 7 + marginTop, widthButton, heightButton/2);
		
		l1.addActionListener(this);
		l2.addActionListener(this);
		l3.addActionListener(this);
		l4.addActionListener(this);
		
		carHorizontal.addActionListener(this);
		carVertical.addActionListener(this);
		camionHorizontal.addActionListener(this);
		camionVertical.addActionListener(this);
		player.addActionListener(this);
		
		clearLevel.addActionListener(this);
		exit.addActionListener(this);
		
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		
		this.add(carHorizontal);
		this.add(carVertical);
		this.add(camionHorizontal);
		this.add(camionVertical);
		this.add(player);
		
		this.add(clearLevel);
		this.add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(carHorizontal == (JButton) e.getSource()) {
			System.out.println("Car Horizontal");
			Manager.type = "CarHorizontal";
		}
		
		if(carVertical == (JButton) e.getSource()) {
			System.out.println("Car Vertical");		
			Manager.type = "CarVertical";
		}
		
		if(camionHorizontal == (JButton) e.getSource()) {
			System.out.println("Camion Horizontal");
			Manager.type = "CamionHorizontal";
		}
		
		if(camionVertical == (JButton) e.getSource()) {
			System.out.println("Camion Vertical");
			Manager.type = "CamionVertical";
		}
		
		if(player == (JButton) e.getSource()) {
			System.out.println("Player");
			Manager.type = "Player";
		}
		
		if(clearLevel == (JButton) e.getSource()) {
			System.out.println("clearLevel");
			Matrix.clearLevel();
		}
		
		if(exit == (JButton) e.getSource()) {
			System.out.println("Exit");
			System.exit(0);
		}
		
		
		
		if(l1 == (JButton) e.getSource()) {
			System.out.println("Create Level 1");
			Manager.level1();
		}
		
		if(l2 == (JButton) e.getSource()) {
			System.out.println("Create Level 2");
			Manager.level2();
		}
		
		if(l3 == (JButton) e.getSource()) {
			System.out.println("Create Level 3");
			Manager.level3();
		}
		
		if(l4 == (JButton) e.getSource()) {
			System.out.println("Create Level 4");
			Manager.level4();
		}
		
	}
	
	

}
