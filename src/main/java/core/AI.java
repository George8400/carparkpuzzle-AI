package core;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import manager.Manager;
import model.Car;
import model.Matrix;


public class AI {

	DesktopService service;
	Handler handler;
	
	InputProgram program;
	InputProgram facts;
	
	public AI() {
		this.init();
	}
	
	private void init() {
		// creare un oggetto DLV in base al sistema operativo
		// service = new DLV2DesktopService("lib/dlv2.exe");
		
		// creare il gestore del sistema passandogli il service creato in precedenza
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
		
		program = new ASPInputProgram();
		facts = new ASPInputProgram();
		try {
			ASPMapper.getInstance().registerClass(Car.class);
		}catch (ObjectNotValidException | IllegalAnnotationException e1) {
		      e1.printStackTrace();
	    }
		
		
		program.addFilesPath("encoding/test");
	    
	
		// aggiungere uno o pi� oggetti per formare il programma logico:
			// tramite file:
			// encoding.addFilesPath("encoding/encoding");
			
			// sotto forma di oggetti:
			//program.addObjectInput(new Car(1,2,2,1,));
			
		// indicare all'handler gli oggetti di tipo ASPInputProgram
		//handler.addProgram(program);
		
		// createFacts();
	}
	
	public void addFact(Car car) {
		try {
			facts.addObjectInput(car);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean generateFacts() {
		
		int rows = Matrix.rows;
		int cols = Matrix.cols;
		
		
		int[][] matrix;
		String[][] typeBlockMatrix;
		int[][] idSingleObject;
		
		matrix = Matrix.getMatrix();
		typeBlockMatrix = Matrix.getTypeBlockMatrix();
		idSingleObject = Matrix.getIdSingleObject();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				
			}
		}
		
		
		return true;
	}
	
	public void playAI() {
		
		System.out.println("PLAYAI");
		
		handler.addProgram(program);
		handler.addProgram(facts);
		
		System.out.println("facts: " + facts.getPrograms());
		System.out.println("program: " + program.getPrograms());
		
		Output output = handler.startSync();
		
		AnswerSets answersets = (AnswerSets) output;
		
		// per vedere gli answersets generati
		/*
		for(AnswerSet a: answersets.getAnswersets()) {
			System.out.println("");
			System.out.println(a.toString());
		}
		*/
		// per convertire gli answersets generati in oggetti
		
		for(AnswerSet a: answersets.getAnswersets()) {
			System.out.println("Sono nel ciclo");
			System.out.println(a.toString());
		      try { 
		        for(Object obj: a.getAtoms()){
					if(!(obj instanceof Car))
						continue;
					
					Car car = (Car) obj;
					
					System.out.println("Sono il risultato dell'AI:  TIPO: " + car.getType() + " ID: " + car.getId()
					+ " X: " + car.getX()+ " Y: " + car.getY());
					
					Manager.updateCar(car);

				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		next();
	}
	
	
	public void next() {
		
		if(Matrix.isWin()) {
			System.out.println("Vittoria! L'AI ha vinto la sfida");
			return;
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		program.clearAll();
		facts.clearAll();
		
		playAI();
	}

}






