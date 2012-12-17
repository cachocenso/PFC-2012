/**
 * 
 */
package edu.uoc.pfc.formwork.ui;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;


/**
 * Clase abstracta de la que derivan todos los 
 * controloladores de aplicaciones Formwork.
 * 
 * @author Alberto Díaz en 22/11/2012
 */
public abstract class GenericController implements IController {
		public void presentar() {
			throw new NotImplementedException();
		}
		public abstract void onEvent(FormworkEvent ev);
}
