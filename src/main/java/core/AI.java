package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
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
import model.Move;


public class AI {

	DesktopService service;
	Handler handler;
	
	InputProgram program;
	InputProgram facts;
	
	ArrayList<Move> objectsUpdate;	// array temporaneo per l'aggiornamento del 'move' degli oggetti
	ArrayList<Move> newObjectsUpdate;
	
	public AI() {
		this.init();
		objectsUpdate = new ArrayList<Move>();
		newObjectsUpdate = new ArrayList<Move>();
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
			ASPMapper.getInstance().registerClass(Move.class);
		}catch (ObjectNotValidException | IllegalAnnotationException e1) {
		      e1.printStackTrace();
	    }
		
		
		//program.addFilesPath("encoding/test");
		//handler.addProgram(program);
		
		
		OptionDescriptor option= new OptionDescriptor("--no-facts");
		OptionDescriptor option2= new OptionDescriptor("--printonlyoptimum");
		
		//handler.addOption(option);
		handler.addOption(option2);
		
	
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
		
		for(Car car: Manager.object ) {
			try {
				facts.addObjectInput(car);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return true;
	}
	
	public void playAI() {
		
		System.out.println("PLAYAI");
		
		generateFacts();
		
		program.addFilesPath("encoding/test");
		handler.addProgram(program);
		handler.addProgram(facts);
		
		System.out.println("facts: " + facts.getPrograms());
		System.out.println("program: " + program.getStringOfFilesPaths());
		
		Output output = handler.startSync();
		
		AnswerSets answersets = (AnswerSets) output;
		
		// per vedere gli answersets generati
		/*
		for(AnswerSet a: answersets.getAnswersets()) {
			System.out.println("");
			System.out.println(a.toString());
		}
		*/
		
		facts.clearAll();	// rimuoviamo i fatti già analizzati (provvisorio)
		program.clearAll();
		
		
		// per convertire gli answersets generati in oggetti
		for(AnswerSet a: answersets.getAnswersets()) {
			System.out.println("Sono nel ciclo dell'AI");
			System.out.println(a.toString());
		      try { 
		        for(Object obj: a.getAtoms()){
					if(!(obj instanceof Move))
						continue;
					
					Move car = (Move) obj;
					
					objectsUpdate.add(car);

				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		init();
		
		// ordiniamo l'array di oggetti in base al time
		if(objectsUpdate.size() != 0) {
			
			Collections.sort(objectsUpdate, new Comparator<Move>() {
				public int compare(Move a, Move b) {
					return a.getTime() - b.getTime();
				}
			});
			
			 for(Move m: objectsUpdate) {
				System.out.println(m.getTime());
			} 
			tickUpdateRenderObject(objectsUpdate);
		}
		
		
		
		objectsUpdate.clear();
		
		next();
	}
	
	
	public void next() {
		
		System.out.println("Altro giro");
		
		if(Matrix.isWin()) {
			System.out.println("Vittoria! L'AI ha vinto la sfida");
			JOptionPane.showMessageDialog(null, "Vittoria! L'AI ha vinto la sfida");
			return;
		}
		
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		playAI();
	}
	
	
	public void tickUpdateRenderObject(ArrayList<Move> cars) {
		
		int countCycles = 0;
		
		while(cars.size() != 0) {
		
			System.out.println("Sono nel while, ciclo: " + countCycles +"   cars.size(): " + cars.size());
			
			for(Move car: cars) {
				
				Car tempCar = new Car(car.getX(), car.getY(), car.getWidth(), car.getHeight(), car.getType().substring(1, car.getType().length()-1), car.getId()-1);
				System.out.println("AI.tickUpdateRenderObject(): new Car("+ tempCar.getX() + ", " + tempCar.getY() + ", " + tempCar.getWidth() + ", " + tempCar.getHeight() + ", " + tempCar.getType() + ", " + tempCar.getId() + ")");
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("");
				System.out.println("AI.tickUpdateRenderObject(" + car.getType().substring(1, car.getType().length()-1)  + ") ID: " + car.getId()
				+ " X: " + car.getX()+ " Y: " + car.getY());
				
				// se l'aggiornamento di car va a buon fine, rimuovi l'oggetto dall'array, poichè aggiornato 
				if(Manager.updateCar(tempCar)) {
					cars.remove(car);
					break;
				}
			}
			
			countCycles++;
		}
			
	}
	/*
	public static boolean isFree(Car car) {
			
			int x = car.getX();
			int y = car.getY();
			int width = car.getWidth();
			int height = car.getHeight();
			
			System.out.println("Siamo in isFree: ");
			System.out.println("X: " + x + "  Y: " + y);
			System.out.println("Width: " + width + " Height: " + height);
			
			
			if( (x + width > cols || y + height > rows)) {
				System.out.println("Celle X: " + x + " Y: " + y + " già occupate, scegliere altre celle");
				return false;
			}
			
			for(int i = y; i < y + height; i++) 
				for(int j = x; j < x + width; j++) {
					if(matrix[i][j] != 0) {
						System.out.println("isNotFree:  matrix[" + i + "][" + j + "]");
						return false;
					}
				}
			
			return true;
		}
	*/

}

class TimeSorter implements Comparator<Move> 
{
    @Override
    public int compare(Move first, Move second) {
        return first.getTime() - second.getTime();
    }
}





