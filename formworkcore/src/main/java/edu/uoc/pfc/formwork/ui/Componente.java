package edu.uoc.pfc.formwork.ui;

/**
 * Clase abstracta base de todos los componentes
 * de servidor de formwork
 * @author Alberto Díaz en 16/12/2012
 */
public abstract class Componente {
	/**
	 * Identificador único del componente
	 */
	private String id;
	
	/**
	 * Getter para id
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter para el id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Transforma el contenido de este componente en código HTML.
	 * 
	 * @return el componente como HTML
	 */
	public abstract String render();

}
