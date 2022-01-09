package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import manager.Manager;

@Id("move")
public class Move{
	
	@Param(0)
	private int x; // coordinate
	
	@Param(1)
	private int y;
	
	@Param(2)
	private int width; 
	
	@Param(3)
	private int height;
	
	@Param(4)
	private String type;
	
	@Param(5)
	private int id;
	
	@Param(6)
	private int time;
	

	Manager objectManager;
	Color color;
	
	public Move() {}

	public Move(int x, int y, int width, int height, String type, int id, int time) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.id = id + 1;
		this.setColor(type);
	}
		
	

	
	public void tick(ArrayList<Move> object) {
		// TODO Auto-generated method stub

	}

	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(getX()*100 + 8, getY()*100 + 8, getWidth()*100 - 16, getHeight()*100 - 16);
	}
	
	@SuppressWarnings("unused")
	private void Collision(ArrayList<Move> object) {
		for(int i = 0; i < Manager.object.size(); i++) {
			
		}
	}
	
	public void setColor(String type) {
		
		switch (type) {
		case "Player":
			this.color = Color.red;
			break;
		case "CarHorizontal":
			this.color = Color.cyan;
			break;
		case "CarVertical":
			this.color = Color.cyan;
			break;
		case "CamionHorizontal":
			this.color = Color.green;
			break;
		case "CamionVertical":
			this.color = Color.green;
			break;
		default:
			this.color = Color.DARK_GRAY;
			break;
		}
		
	
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, getWidth(), getHeight());
	}


	
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Manager getObjectManager() {
		return objectManager;
	}

	public void setObjectManager(Manager objectManager) {
		this.objectManager = objectManager;
	}

	public Color getColor() {
		return color;
	}




	
	

	
	


	
	


}
