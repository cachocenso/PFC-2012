/**
 * 
 */
package edu.uoc.pfc.formwork.ui;

import edu.uoc.pfc.formwork.xml.TipoTipoApartado;

/**
 * @author Alberto Díaz en 11/11/2012
 */
public enum TipoApartado {
	identificacion, devengo, partidas, pago;
	
	/**
	 * Transforma un objeto TipoTipoApartado que proviene del parse de
	 * la página fwp en un enum TipoApartado para el árbol de componentes.
	 * 
	 * @param tipoTipoApartado
	 * @return
	 */
	public static TipoApartado fromTipoTipoApartado(TipoTipoApartado tipoTipoApartado) {
		for (TipoApartado a : TipoApartado.values()) {
			if (a.name().equals(tipoTipoApartado.value())) {
				return a;
			}
		}
		throw new IllegalArgumentException(tipoTipoApartado.value());
	}

}
