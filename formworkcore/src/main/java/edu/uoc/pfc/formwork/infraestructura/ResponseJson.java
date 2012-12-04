/**
 * 
 */
package edu.uoc.pfc.formwork.infraestructura;

import java.util.List;

/**
 * Clase que encapsula la respuesta a una petición
 * AJAX.
 * 
 * @author Alberto Díaz en 04/12/2012
 */
public class ResponseJson {
	public enum Resultado {
		SUCCESS, ERROR, WARNING
	}
	
	private Resultado resultado;
	private List<?> responseObjects;
	
	public Resultado getResultado() {
		return resultado;
	}
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	public List<?> getResponseObjects() {
		return responseObjects;
	}
	public void setResponseObjects(List<?> responseObjects) {
		this.responseObjects = responseObjects;
	}
	
	
}
