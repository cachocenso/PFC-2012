package edu.uoc.pfc.formwork.ui;

public abstract class Partida<T> extends Componente {
	private T value;
	private String etiqueta;
	private boolean calculado;

	/*
	 * Almacena el valor con formato de la partida.
	 * Es necesario para que Gson pueda deserializar
	 * correctamente este valor cuando se envíe respuesta
	 * al cliente.
	 */
	private String formattedValue;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
		formatValue();
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

	
	public String getFormattedValue() {
		return formattedValue;
	}
	
	protected abstract void formatValue();

	public void setFormattedValue(String formattedValue) {
		this.formattedValue = formattedValue;
	}
}
