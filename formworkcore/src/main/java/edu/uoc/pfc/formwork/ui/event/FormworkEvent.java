/**
 * 
 */
package edu.uoc.pfc.formwork.ui.event;

import edu.uoc.pfc.formwork.ui.Componente;

/**
 * Clase que encapsula un evento que proviene desde
 * la interfaz gráfica.
 * 
 * @author Alberto Díaz en 22/11/2012
 */
public class FormworkEvent {

	private Componente target;
	private String value;
	
	/***  GETTERS Y SETTERS ***/
	/**
	 * @return
	 */
	public Componente getTarget() {
		return target;
	}

	/**
	 * @param target
	 */
	public void setTarget(Componente target) {
		this.target = target;
	}

	/**
	 * @param parameter
	 */
	public void setNewValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
