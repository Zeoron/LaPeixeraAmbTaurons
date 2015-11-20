package net.srovira.peixera;

import java.awt.Color;
import java.util.Random;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class App extends GraphicsProgram {
	//VARIABLES NECESARIES PER EXECUTAR EL PROGRAMA
	private static final long serialVersionUID = -3496973863839908191L;
	private static final int [] POSSIBLESANGLES = {0,90,180,270};
	
	Random aleatori = new Random();
	Peixera lloc;

	//METODE PRINCIPAL D'EXECUCIÓ DEL PROGRAMA
	public void run() {
		setSize(800, 600);
		setBackground(Color.LIGHT_GRAY);
		pause(100);
		
		lloc = new Peixera(this, getWidth(), getHeight());
		
		invocarPeixosNormals();
		invocarTaurons();
		
		pause(1000);
		lloc.posicionarPeix();
		pause(1000);
		
		while (lloc.finalitza()) {
			lloc.mourePeixos();
			pause(10);
		}
	}
	
	//METODE DE CREACIÓ DE PEIXOS
	public void invocarPeixosNormals() {
		for (int i = 0; i<30;i++) {
			Peix temp = crearPeixNormal();
			lloc.crearPeixos(temp);
		}
	}
	public void invocarTaurons() {
		for (int i = 0; i<6;i++) {
			Peix temp = crearTauro();
			lloc.crearPeixos(temp);
		}
	}

	public Peix crearPeixNormal() {
		GImage pez;
		int anglePeix = POSSIBLESANGLES[aleatori.nextInt(POSSIBLESANGLES.length)];
		int sexePeix = aleatori.nextInt(2);
		String textPeix = "peix" + sexePeix + "" + anglePeix + ".png";
		pez = new GImage(textPeix);
		add(pez);
		Peix temp = new PeixNormal(pez, anglePeix, sexePeix);
		return temp;
	}
	
	public Peix crearTauro() {
		GImage taur;
		int angleTauro = POSSIBLESANGLES[aleatori.nextInt(POSSIBLESANGLES.length)];
		int sexeTauro = aleatori.nextInt(2);
		String textTauro = "tauro" + sexeTauro + "" + angleTauro + ".png";
		taur = new GImage(textTauro);
		/*taur.scale(0.8);*/
		add(taur);
		Peix temp = new Tauro (taur, angleTauro, sexeTauro);
		return temp;
	}
	
	
	
	//METODE PER VARIAR LA ESCALA DELS PEIXOS I OBTENIR DIFERENTS MIDES
	/*public double midaAleatoria() {
		double result;
		double start = 0.5;
		double end = 1;
		double random = aleatori.nextDouble();
		result = start + (random * (start-end));
		return result;
		
		
	}*/

}
