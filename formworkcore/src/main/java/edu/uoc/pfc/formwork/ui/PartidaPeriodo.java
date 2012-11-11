/**
 * 
 */
package edu.uoc.pfc.formwork.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alberto Díaz en 11/11/2012
 */
public class PartidaPeriodo extends PartidaCadena {
	private List<String> periodos = new ArrayList<String>();

	public List<String> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
	}
	
	
}
