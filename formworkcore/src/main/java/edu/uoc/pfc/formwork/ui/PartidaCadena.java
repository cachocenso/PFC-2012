package edu.uoc.pfc.formwork.ui;

public class PartidaCadena extends Partida<String> {

	@Override
	public String render() {
		return null;
		
	}

	@Override
	protected void formatValue() {
		setFormattedValue(getValue());
		
	}

	
}
