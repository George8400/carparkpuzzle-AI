package model;

import manager.Manager;

public class Matrix {
	
	public static int numberObjects = 0; // numero di oggetti inseriti -> id dei singoli oggetti (progressivo)
	
	public static int rows = 6;
	public static int cols = 6;
	
	private static int[][] matrix = new int[rows][cols];
	private static String[][] typeBlockMatrix = new String[rows][cols];
	private static int[][] idSingleObject = new int[rows][cols];
	
	private static Matrix instance = null;
	
	private static Manager manager;
	
	protected Matrix() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				matrix[i][j] = 0;
				typeBlockMatrix[i][j] = null;
				idSingleObject[i][j] = 0;
			}
		}
		
	}

	
	public static Matrix getInstance(Manager manager) {
		if(instance == null) {
			instance = new Matrix();
			Matrix.manager = manager;
			manager.level1();
			numberObjects = Manager.object.size();
		}
		return instance;
	}
	
	public static synchronized boolean isFree(Car car) {
		
		int x = car.getX();
		int y = car.getY();
		int width = car.getWidth();
		int height = car.getHeight();
		
		// System.out.println("Siamo in isFree: ");
		// System.out.println("X: " + x + "  Y: " + y);
		// System.out.println("Width: " + width + " Height: " + height);
		
		
		if( (x + width > cols || y + height > rows)) {
			System.out.println("Celle X: " + x + " Y: " + y + " si sta sforando la matrice");
			return false;
		}
		
		for(int i = y; i < y + height; i++) 
			for(int j = x; j < x + width; j++) {
				if(matrix[i][j] != 0) {
					System.out.println("Matrix.isFree(" + car.getType() + "): matrix[" + i + "][" + j + "] NOT FREE");
					return false;
				}
			}
		
		
		return true;
	}
	
	public static synchronized boolean addInMatrix(Car car) {
		
		int x = car.getX();
		int y = car.getY();
		String type = car.getType();
		int id = car.getId();
		
		// Se non ci sono abbastanza celle libere, la funzione termina immediatamente
		if( !Matrix.isFree(car) )
			return false;
		
		int width = 0, height = 0;
		
		width = Matrix.getWidthObject(type);
		height = Matrix.getHeightObject(type);
		
		for(int i = y; i < y + height; i++) {
			for(int j = x; j < x + width; j++){
				matrix[i][j] = 1;
				typeBlockMatrix[i][j] = type;
				idSingleObject[i][j] = id;
			}
		}
		
		Matrix.numberObjects++;
		return true;
	}
	
	public static synchronized void remove(Car car) {
		
		int x = car.getX();
		int y = car.getY(); 
		String type = car.getType();
		
		int width = 0, height = 0;
		width = Matrix.getWidthObject(type);
		height = Matrix.getHeightObject(type);
		
		for(int i = y; i < y + height; i++) {
			for(int j = x; j < x + width; j++){
				matrix[i][j] = 0;
				typeBlockMatrix[i][j] = null;
				idSingleObject[i][j] = -1;
			}
		}
		System.out.println("Matrix.remove(" + car.getType() + ") Remove car ");
		Matrix.numberObjects--;
		// printMatrix();
	}
	
	public static boolean isWin() {
		
		int playerX = 0;
		
		for (int i = 0; i < Manager.object.size(); i++) {
			if(Manager.object.get(i).getType() == "Player") {
				playerX = Manager.object.get(i).getX(); 
				System.out.println("MAtrix.isWin(): playerX " + playerX);
			}
		}
		
		if(playerX == 4)
			return true;
		
		for(int i = playerX; i < cols; i++) {
			if(typeBlockMatrix[2][i] != null && typeBlockMatrix[2][i] != "Player") {
				System.out.println("Riprova, la strada è ancora occupata!");
				return false;
			}
		}
		System.out.println("Vittoria!!!");
		return true;
	}
	
	public static int getWidthObject(String type) {
		int width = 0;
		switch (type) {
			case "CarHorizontal":
				width = 2;
				break;
			case "CarVertical":
				width = 1;
				break;
			case "CamionHorizontal":
				width = 3;
				break;
			case "CamionVertical":
				width = 1;
				break;
			case "Player":
				width = 2;
				break;
	
			default:
				break;
		}
		
		//System.out.println("Matrix.getWidthObject(" + type + ") -> width: " + width);
		
		return width;
	}
	
	public static int getHeightObject(String type) {
		int height = 0;
		switch (type) {
			case "CarHorizontal":
				height = 1;
				break;
			case "CarVertical":
				height = 2;
				break;
			case "CamionHorizontal":
				height = 1;
				break;
			case "CamionVertical":
				height = 3;
				break;
			case "Player":
				height = 1;
				break;
	
			default:
				break;
		}
		
		//System.out.println("Matrix.getHeightObject(" + type + ") -> height: " + height);
		
		return height;
	}
	
	public static int point2D(int p) {
		if(p/100 == 0 && p != 0)
			return 0;
		
		return p = p/100;
	}
	
    public static synchronized void printMatrix() {
	   System.out.println("------------------");
	   for(int i = 0; i < rows; i++) {
           for(int j = 0; j < cols; j++) 
              System.out.print(matrix[i][j] + " ");
           System.out.println();
	   }
	   System.out.println("------------------");
   }

    // TODO: rimuovere anche gli oggetti dall'array di manager
	public static void clearLevel() {
		for(int i = 0; i < rows; i++) {
	        for(int j = 0; j < cols; j++) {
	            matrix[i][j] = 0;
	            typeBlockMatrix[i][j] = null;
	            }
	        }
		Matrix.manager.clearLevel();
	}

	public static int[][] getMatrix(){
		return matrix;
	}
	public static String[][] getTypeBlockMatrix(){
		return typeBlockMatrix;
	}
	public static int[][] getIdSingleObject(){
		return idSingleObject;
	}

	public static boolean updateMatrix(Car car) {
		
		remove(car);
		addInMatrix(car);
		
		return true;
	}

}
