package edu.uoc.pfc.formwork.ui;

import java.util.List;


public class PartidaLista extends Partida<String> {

	
	private int indexSeleccionado;
	private List<String> values;
	
	@Override
	public String render() {
		return "Soy la partida" + getId();
		
	}

	public int getIndexSeleccionado() {
		return indexSeleccionado;
	}

	public void setIndexSeleccionado(int indexSeleccionado) {
		this.indexSeleccionado = indexSeleccionado;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	@Override
	public String getValue() {
		if (values == null || indexSeleccionado < 0 || indexSeleccionado >= values.size()) {
			return null;
		}
		
		return values.get(indexSeleccionado);
	}

}
