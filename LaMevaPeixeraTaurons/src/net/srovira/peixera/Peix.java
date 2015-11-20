package net.srovira.peixera;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public abstract class Peix {
	//ATRIBUTS DEL PEIX
	GImage imatge;
	int velocitat = 1;
	int angle;
	int sexe;
	boolean criant = false;
	boolean eliminar = false;
	
	public abstract boolean menjoAlgu(Peix p);
	

	//CONSTRUCTOR DEL PEIX
	public Peix(GImage image, int angle, int sexe) {
		imatge = image;
		this.angle = angle;
		this.sexe = sexe;
	}
	
	public boolean colisiona(Peix p) {
		if (imatge.getBounds().intersects(p.espaiOcupa())) {
			return true;
		}
		return false;
	}
	
	public boolean isCriant() {
		return criant;
	}

	public void setCriant(Boolean es) {
		criant = es;
	}
	
	
	//GETTERS AND SETTERS
	public int getSexe() {
		return sexe;
	}
	public boolean isEliminar() {
		return eliminar;
	}
	public void setEliminar() {
		imatge.setVisible(false);
		this.eliminar = true;
	}
	public int getAmplada() {
		return (int) imatge.getWidth();
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int newAngle) {
		angle = newAngle;
	}
	public void setImage(GImage newImatge) {
		imatge = newImatge;
	}
	public GRectangle espaiOcupa() {
		return imatge.getBounds();
	}
	public int getAltura() {
		return (int)imatge.getHeight();
	}
	
	//POSICIONAMENT I MOVIMENT DEL PEIX
	public void movimentPeix() {
		imatge.movePolar(velocitat, angle);
	}
	public void posicionarPeix(int x, int y) {
		imatge.setLocation(x, y);
	}
}
