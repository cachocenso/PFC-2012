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
public interface IController {
	void presentar();
	void onEvent(FormworkEvent evt);
}