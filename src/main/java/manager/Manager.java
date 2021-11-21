package manager;


import java.awt.Graphics;
import java.util.ArrayList;

import core.AI;
import core.Game;
import id.Type;
import model.Car;
import model.Matrix;
import model.Move;


public class Manager
{
	public static ArrayList<Car> object = new ArrayList<Car>();		// lista oggetti da stampare a video
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

	
	// crea l'oggetto car, lo aggiunge alla matrix, all'array del rendering e ai fatti dell'AI
	public static boolean createObject(int x, int y, String type) {
		
		int width = Matrix.getWidthObject(type);
		int height = Matrix.getHeightObject(type);
		
		
		int xx = Matrix.point2D(x);
		int yy = Matrix.point2D(y);
		
		Car car = new Car(xx, yy, width, height, type, Matrix.numberObjects);
		
		System.out.println("Manager.createObject(" + type + "): new Car("+ xx + ", " + yy + ", " + width + ", " + height + ", " + type + ", " + Matrix.numberObjects + ")");
		
		// se l'aggiunta dell'oggetto nella matrice va a buon fine, aggiungi l'oggetto alla lista del rendering e ai fatti dell'AI 
		if( Matrix.addInMatrix(car) ) {
			addObject(car);
			//Manager.ai.addFact(car);
			System.out.println("Manager.createObject(" + type + "): Oggetto aggiunto con successo nella matrice, nella lista render e nei fatti");
			Matrix.printMatrix();
			return true;
		}
		
		return false;
	}
	

	/*-----------------------------
	 Metodo per 'pulire' il livello
	 ------------------------------*/
	public void clearLevel() {
		object.clear(); 
		// TODO: aggiungere ai.clearAll() per svuotare i fatti (il program)
		//createPlayer();
	}

	/*----------
	 Metodi aggiungi e rimuovi oggetti nel gioco
	 -----------*/
	public static void addObject(Car object) 
	{
		Manager.object.add(object);
	}

	public static void removeObject(Car object)
	{
		Manager.object.remove(object);
	}

	// aggiorna gli oggetti 
	public static boolean updateCar(Car car) {
		
		Car oldCar = null;
		
		// rimuoviamo l'oggetto dalla matrice e dall'array del rendering
		for (int i = 0; i < object.size(); i++) {
			if(car.getId() == object.get(i).getId()) {
				oldCar = object.get(i);
				System.out.println("Manager.UpdateCar(" + car.getType() + "): Rimuovo l'oggetto con ID: " + car.getId());
				Matrix.remove(object.get(i));	// rimuovo l'oggetto dalla matrice
				object.remove(i);	// rimuovo l'oggetto dalla lista degli oggetti per il rendering
				break;
			}
		}
		
		
		System.out.println("Manager.updateCar(" + car.getX()*100 + ", "+ car.getY()*100+", "+ car.getType()+ ");");
		
		// creiamo il nuovo oggetto partendo dai dati dell'AI
		// if(Manager.createObjectUpdate(car)) {
		//if(Manager.createObject(car.getX()*100, car.getY()*100, car.getType().substring(1, car.getType().length()-1))) {
		if(Manager.createObjectUpdate(car)) {
			System.out.println("Oggetto aggiunto con successo, ecco la matrice aggiornata");
			Matrix.printMatrix();	
			
			return true;
		}
			
		System.out.println("updateCar("+ car.getType() + ") Aggiunta nuovo oggetto fallito, riaggiungo il vecchio oggetto alla lista render");
		Matrix.addInMatrix(oldCar);
		object.add(oldCar);

		
		return false;
	}
	
	
	// crea l'oggetto car, lo aggiunge alla matrix, all'array del rendering e ai fatti dell'AI
	public static boolean createObjectUpdate(Car car) {
		
		System.out.println("Manager.createObjectUpdate(): new Car("+ car.getX() + ", " + car.getY() + ", " + car.getWidth() + ", " + car.getHeight() + ", " + car.getType() + ", " + car.getId() + ")");
		
		// se l'aggiunta dell'oggetto nella matrice va a buon fine, aggiungi l'oggetto alla lista del rendering e ai fatti dell'AI 
		if( Matrix.addInMatrix(car) ) {
			addObject(car);
			//Manager.ai.addFact(car);
			System.out.println("Manager.createObject(" + type + "): Oggetto aggiunto con successo nella matrice, nella lista render e nei fatti");
			Matrix.printMatrix();
			return true;
		}
		
		return false;
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
	
	public void level1() {
		createObject(0, 0, "CarHorizontal");
		createObject(400, 0, "CarHorizontal");
		createObject(0, 100, "CarVertical");
		createObject(0, 300, "CamionVertical");
		createObject(100, 200, "Player");
		createObject(300, 200, "CamionVertical");
		createObject(300, 500, "CarHorizontal");
		createObject(400, 100, "CarVertical");
		createObject(500, 100, "CarVertical");
	}

}
