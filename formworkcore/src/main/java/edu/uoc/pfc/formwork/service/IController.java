/**
 * 
 */
package edu.uoc.pfc.formwork.service;

import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Interfaz que tienen que implementar los controladores
 * de Formwork. Aunque lo habitual es heredar de GenericController.
 * 
 * @author Alberto Díaz en 22/11/2012
 */
/**
 * @author albertodiaz
 *
 */
public interface IController {
	/**
	 * Invocado cuando el usuario hace clic en el botón
	 * de enviar o firmar y enviar.
	 */
	void onPresentar();
	
	/**
	 * Devuelve un registro listo para enviar (y en su caso, firmar)
	 * con el contenido del formulario.
	 * 
	 * @return
	 */
	String getRegistro();
	/**
	 * Invocado cuando se produce un cambio en el valor de cualquiera de los
	 * elementos del formulario.
	 * 
	 * @param evt
	 */
	void onEvent(FormworkEvent evt);	
}
