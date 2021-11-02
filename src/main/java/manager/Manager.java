package manager;


import java.awt.Graphics;
import java.util.ArrayList;

import core.AI;
import core.Game;
import id.Type;
import model.Car;
import model.Matrix;


public class Manager
{
	public static ArrayList<Car> object = new ArrayList<Car>();
	public static String type = null;
	public static AI ai;
	
	public Manager(AI ai) {
		Manager.ai = ai;
	}

	/*----------
	 Logic Game
	 -----------*/
	public void tick() {
		for (int i = 0; i < object.size(); i++) {  // aggiorna tutti gli oggetti della lista
			object.get(i).tick(object);
		}
	}

	/*----------
	 Graphic Game
	 -----------*/
	public void render(Graphics g) // aggiorna tutti gli oggetti della lista
	{
		for (int i = 0; i < object.size(); i++) {
			object.get(i).render(g);	
		}
	}
	
	public void createPlayer() {
		Car player = new Car(0, 2, 2, 1, "Player", Matrix.numberObjects);
		object.add(player);
		Matrix.addInMatrix(player);
		
		Manager.ai.addFact(player);
	}

	
	public boolean createObject(int x, int y, String type) {
		
		int width = Matrix.getWidthObject(type);
		int height = Matrix.getHeightObject(type);
		int xx = Matrix.point2D(x);
		int yy = Matrix.point2D(y);
		
		Car car = new Car(xx, yy, width, height, type, Matrix.numberObjects);
		
		if( Matrix.addInMatrix(car) ) {
			this.addObject(car);
			ai.addFact(car);
			System.out.println("Oggetto aggiunto con successo");
			Matrix.numberObjects++;
			Matrix.printMatrix();
		}
		
		return true;
	}
	

	/*-----------------------------
	 Metodo per 'pulire' il livello
	 ------------------------------*/
	public void clearLevel() {
		object.clear(); 
		// TODO: aggiungere ai.clearAll() per svuotare i fatti (il program)
		createPlayer();
	}

	/*----------
	 Metodi aggiungi e rimuovi oggetti nel gioco
	 -----------*/
	public void addObject(Car object) 
	{
		Manager.object.add(object);
	}

	public static void removeObject(Car object)
	{
		Manager.object.remove(object);
	}

	public static void updateCar(Car car) {
		
		for (int i = 0; i < object.size(); i++) {
			if(car.getId() == object.get(i).getId()) {
				System.out.println("Rimuovo l'oggetto: " + car.getId());
				// TODO: update delle coordinate dell'oggetto car
				object.remove(i);
			}
		}
		
		System.out.println("Numero oggetti nell'array: " + object.size());
		
		Matrix.remove(car);
		//car.setColor(car.getType());
		//object.add(car);
		//Matrix.addInMatrix(car);
		
	}
	
	public static void infoObjects() {
		System.out.println("Numero oggetti inseriti nell'array: " + object.size());
		System.out.println("Oggetti nell'array: ");
		for(int i = 0; i < object.size(); i++) {
			System.out.println("ID: " + object.get(i).getId());
			System.out.println("Type: " + object.get(i).getType());
			System.out.println("X: " + object.get(i).getX());
			System.out.println("Y: " + object.get(i).getY());
			System.out.println("W: " + object.get(i).getWidth());
			System.out.println("H: " + object.get(i).getHeight());
		}
	}

}
