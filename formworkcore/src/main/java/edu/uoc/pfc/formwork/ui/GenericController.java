/**
 * 
 */
package edu.uoc.pfc.formwork.ui;

import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Clase abstracta de la que derivan todos los 
 * controloladores de aplicaciones Formwork.
 * 
 * @author Alberto Díaz en 22/11/2012
 */
public abstract class GenericController implements IController {

	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.IController#render()
	 */
	public void render() {
		// TODO Auto-generated method stub

	}

	public abstract void onEvent(FormworkEvent evt);
}
