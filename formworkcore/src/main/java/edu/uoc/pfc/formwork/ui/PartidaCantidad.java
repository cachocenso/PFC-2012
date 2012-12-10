package edu.uoc.pfc.formwork.ui;

import java.math.BigDecimal;

public class PartidaCantidad extends Partida<BigDecimal> {
	private boolean admiteNegativos;

	/**
	 * 
	 */
	public PartidaCantidad() {
		setValue(BigDecimal.ZERO);
	}

	public boolean isAdmiteNegativos() {
		return admiteNegativos;
	}

	public void setAdmiteNegativos(boolean admiteNegativos) {
		this.admiteNegativos = admiteNegativos;
	}

	@Override
	public String render() {
		return "Soy la partida" + getId();
		
	}
	
	
}
