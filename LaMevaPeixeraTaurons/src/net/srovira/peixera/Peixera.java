package net.srovira.peixera;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import acm.graphics.GRectangle;

public class Peixera {
	Random aleatori = new Random();
	// LISTA DE PEIXOS ACTIUS
	List<Peix> peixos = new ArrayList<Peix>();

	List<Peix> bebes = new ArrayList<Peix>();

	// ATRIBUTS DE LA PEIXERA
	int altura;
	int amplada;
	App pantalla;
	/*
	 * GRectangle pantalla = new GRectangle(0 ,0 , amplada, altura); //ESPAI QUE
	 * OCUPA LA PEIXERA (BOUNDS)
	 */

	// CONSTRUCTOR DE PEIXERA
	public Peixera(App pantalla, int x, int y) {
		amplada = x;
		altura = y;
		this.pantalla = pantalla;
	}

	// METODE PER AFEGIR ELS PEIXOS A L'ARRAI DE PEIXOS ACTIUS
	public void crearPeixos(Peix pes) {
		peixos.add(pes);
	}

	// CONTROLS DEL POSICIONAMENT INICIAL DELS PEIXOS (TOTALMENT ALEATORI)
	public void posicionarPeix() {
		for (Peix p : peixos) {
			p.posicionarPeix(aleatori.nextInt(amplada - p.getAmplada()), aleatori.nextInt(altura - p.getAltura()));
		}
	}

	// METODE QUE CONTROLA EL MOVIMENT DELS PEIXOS
	public void mourePeixos() {
		// Recorrer la llista de peixos
		for (Peix p : peixos) {
			/// Si el peix NO esta mort...
			if (!p.isEliminar()) {
				p.movimentPeix(); // Moure els peixos
				colisionaParets(p); // Comprovar les colisions amb les parets
				Peix col = colisionaPeixos(p); // Metode que comprova la
				if (colisionaPeixos(p) != null) { // Si el resultat torna un
													// peix...
					/*
					 * if (p.getSexe()==col.getSexe()) { //Si el sexe es el mateix... 
					 * p.setEliminar(); //Eliminar el peix seleccionat
					 * col.setEliminar(); //Eliminar el peix retorinat per lacolisio 
					 * } else { //Si el sexe es diferent han de criar 
					 * if (p.isCriant() == false && col.isCriant()== false) {
					 * p.setCriant(true); col.setCriant(true); crearPeixBebe(p,
					 * col); } }
					 */
					boolean haMatat = false;
					if (p.menjoAlgu(col)) {
						col.setEliminar();
						haMatat = true;
					}
					if (col.menjoAlgu(p)) {
						p.setEliminar();
						haMatat = true;
					}
					if (!haMatat) {
						if (p.isCriant() == false && col.isCriant()== false) {
							if (p instanceof PeixNormal) {
								crearPeixBebe(p, col);
							} else {
								crearTauroBebe(p, col);
							}
						p.setCriant(true); 
						col.setCriant(true);
					}
					}
						
				} else {
					p.setCriant(false);
				}
				
				// Si el peix SI esta mort...
			}
		}
		colisionsBebes();
	}

	public void crearPeixBebe(Peix p, Peix col) {
		GRectangle posicioFill = p.espaiOcupa().intersection(col.espaiOcupa());
		Peix nouBebe = pantalla.crearPeixNormal();
		bebes.add(nouBebe);
		nouBebe.posicionarPeix((int) posicioFill.getX(), (int) posicioFill.getY());
	}
	
	public void crearTauroBebe(Peix p, Peix col) {
		GRectangle posicioFill = p.espaiOcupa().intersection(col.espaiOcupa());
		Peix nouBebe = pantalla.crearTauro();
		bebes.add(nouBebe);
		nouBebe.posicionarPeix((int) posicioFill.getX(), (int) posicioFill.getY());
	}

	public void colisionsBebes() {
		for (int i = bebes.size() - 1; i >= 0; i--) {
			if (colisionaPeixos(bebes.get(i)) == null) {
				Peix bebe = bebes.get(i);
				peixos.add(bebe);
				bebes.remove(bebe);
			}
		}
	}

	// CONTROL DE LA FINALITZACIÓ DEL PROGRAMA SI HENS QUEDEM SENSE PEIXOS
	public boolean finalitza() {
		int acaba = peixos.size();
		if (acaba == 1) {
			return false;
		}
		return true;
	}

	// METODE QUE CONTROLA LA COLISIÓ DELS PEIXOS AMB LES PARETS DE LA NOSTRA
	// FINESTRA
	public void colisionaParets(Peix p) {
		// Part del programa que fa que els peixos rebotin
		/*
		 * if (p.espaiOcupa().getX()<=pantalla.getX()||
		 * p.espaiOcupa().getX()+p.getAmplada()>=(amplada)) {
		 * p.setAngle(180-p.getAngle()); } if
		 * (p.espaiOcupa().getY()<=pantalla.getY()||
		 * p.espaiOcupa().getY()+p.getAltura()>=(altura)) {
		 * p.setAngle(p.getAngle()*-1); }
		 */

		// PART DEL CODI QUE FA QUE ELS PEIXOS APAREGUIN PER L'ALTRE COSATAT EN
		// CAS DE QUE SURTIN DE LA PANTALLA
		if (p.espaiOcupa().getX() > amplada) {
			int temp1 = (int) p.espaiOcupa().getX() + 1;
			int temp2 = temp1 % amplada;
			int temp3 = temp2 - p.getAmplada();
			p.posicionarPeix(temp3, (int) p.espaiOcupa().getY());
		}
		if (p.espaiOcupa().getX() < 0 - p.getAmplada()) {
			p.posicionarPeix(amplada, (int) p.espaiOcupa().getY());
		}
		if (p.espaiOcupa().getY() > altura) {
			int temp1 = (int) p.espaiOcupa().getY() + 1;
			int temp2 = temp1 % altura;
			int temp3 = temp2 - p.getAltura();
			p.posicionarPeix((int) p.espaiOcupa().getX(), temp3);
		}
		if (p.espaiOcupa().getY() < 0 - p.getAltura()) {
			p.posicionarPeix((int) p.espaiOcupa().getX(), altura);
		}
	}

	public Peix colisionaPeixos(Peix p) {
		for (Peix pes : peixos) {
			if (p != pes && !(pes.isEliminar())) {
				if (p.colisiona(pes)) {
					return pes;
				}
			}
		}
		return null;
	}
}
