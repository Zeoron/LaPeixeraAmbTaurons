package net.srovira.peixera;

import acm.graphics.GImage;

public class Tauro extends Peix {

	public Tauro(GImage image, int angle, int sexe) {
		super(image, angle, sexe);
		// TODO Auto-generated constructor stub
	}
	public boolean menjoAlgu(Peix p) {
		if (p instanceof Tauro && p.getSexe()!=this.getSexe()) {
			return false;
		}
		return true;
	}

}
