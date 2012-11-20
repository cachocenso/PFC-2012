package edu.uoc.pfc.formwork.ui;

public class PartidaCadena extends Partida<String> {

	@Override
	public String render(IRenderer renderer) {
		return "Soy la partida " + getId();
		
	}

}
