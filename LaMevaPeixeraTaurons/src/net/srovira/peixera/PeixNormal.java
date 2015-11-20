package net.srovira.peixera;

import acm.graphics.GImage;

public class PeixNormal extends Peix {

	public PeixNormal(GImage image, int angle, int sexe) {
		super(image, angle, sexe);
	}
	public boolean menjoAlgu(Peix p) {
		if (p instanceof PeixNormal && p.getSexe()==this.getSexe()) {
			return true;
		}
		return false;
	}
	

}
