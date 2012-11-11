package edu.uoc.pfc.formwork.ui;

public abstract class Partida<T> extends Componente {
	private T value;
	
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @param string
	 */
	public void setEtiqueta(String string) {
		// TODO Auto-generated method stub
		
	}

}
