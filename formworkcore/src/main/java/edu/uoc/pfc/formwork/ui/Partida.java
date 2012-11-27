package edu.uoc.pfc.formwork.ui;

public abstract class Partida<T> extends Componente {
	private T value;
	private String etiqueta;
	private boolean calculado;
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @param string
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
		
	}

	/**
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	public boolean isCalculado() {
		return calculado;
	}

	public void setCalculado(boolean calculado) {
		this.calculado = calculado;
	}
}
