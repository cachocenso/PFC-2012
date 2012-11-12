package edu.uoc.pfc.formwork.ui;

import java.math.BigDecimal;

public class PartidaCantidad extends Partida<BigDecimal> {
	private boolean admiteNegativos;


	public boolean isAdmiteNegativos() {
		return admiteNegativos;
	}

	public void setAdmiteNegativos(boolean admiteNegativos) {
		this.admiteNegativos = admiteNegativos;
	}

	@Override
	public void render(IRenderer renderer) {
		// TODO Auto-generated method stub
		
	}
	
	
}
