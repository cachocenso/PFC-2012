package edu.uoc.pfc.formwork.ui;

public class PartidaCadena extends Partida<String> {

	@Override
	public String render() {
		return "Soy la partida " + getId();
		
	}

}
