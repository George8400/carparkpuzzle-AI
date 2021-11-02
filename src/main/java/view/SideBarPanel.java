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
	
	JButton carHorizontal;
	JButton carVertical;
	JButton camionHorizontal;
	JButton camionVertical;
	
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
		int heightButton = 75;
		
		carHorizontal = new JButton("carHorizontal");
		carVertical = new JButton("carVertical");
		camionHorizontal = new JButton("camionHorizontal");
		camionVertical = new JButton("camionVertical");
		clearLevel = new JButton("clearLevel");
		exit = new JButton("Exit");
		
		carHorizontal.setBounds(WIDTH/2 - widthButton/2, HEIGHT/7 * 2, widthButton, heightButton);
		carVertical.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 3 + marginTop, widthButton, heightButton);
		camionHorizontal.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 4 + marginTop, widthButton, heightButton);
		camionVertical.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 5 + marginTop, widthButton, heightButton);
		clearLevel.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 6 + marginTop, widthButton, heightButton/2);
		exit.setBounds(WIDTH/2 - widthButton/2, HEIGHT/8 * 7 + marginTop, widthButton, heightButton/2);
		
		carHorizontal.addActionListener(this);
		carVertical.addActionListener(this);
		camionHorizontal.addActionListener(this);
		camionVertical.addActionListener(this);
		clearLevel.addActionListener(this);
		exit.addActionListener(this);
		
		this.add(carHorizontal);
		this.add(carVertical);
		this.add(camionHorizontal);
		this.add(camionVertical);
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
		
		if(clearLevel == (JButton) e.getSource()) {
			System.out.println("clearLevel");
			Matrix.clearLevel();
		}
		
		if(exit == (JButton) e.getSource()) {
			System.out.println("Exit");
			System.exit(0);
		}
		
	}
	
	

}
