/**
 * 
 */
package edu.uoc.pfc.formworktest.controller;

import javax.servlet.http.HttpSession;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import edu.uoc.pfc.formwork.infraestructura.Session;
import edu.uoc.pfc.formwork.ui.Componente;
import edu.uoc.pfc.formwork.ui.IController;
import edu.uoc.pfc.formwork.ui.Partida;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Controlador de la aplicación de ejemplo
 * del framwork.
 * 
 * @author Alberto Díaz en 23/11/2012
 */
public class TestController implements IController {
	
	@Session
	private HttpSession session;
	
	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.GenericController#onEvent(edu.uoc.pfc.formwork.ui.event.FormworkEvent)
	 */
	public void onEvent(FormworkEvent evt) {
		Componente componente = evt.getTarget();

		KnowledgeBase knowledgeBase = FormworkContext.getKnowledgeBase();
		
		StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
		
		session.insert(componente);
		session.fireAllRules();
		
		((Partida)componente).setValue("00000101D");
	}

}
