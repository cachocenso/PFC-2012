package edu.uoc.pfc.formwork.ui;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
		return null;
	}

	
	@Override
	protected void formatValue() {
		BigDecimal value = getValue();
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		setFormattedValue(decimalFormat.format(value));
	}
}
