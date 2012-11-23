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
import edu.uoc.pfc.formwork.ui.GenericController;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Controlador de la aplicación de ejemplo
 * del framwork.
 * 
 * @author Alberto Díaz en 23/11/2012
 */
public class TestController extends GenericController {
	
	@Session
	private HttpSession session;
	
	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.GenericController#onEvent(edu.uoc.pfc.formwork.ui.event.FormworkEvent)
	 */
	@Override
	public void onEvent(FormworkEvent evt) {
		Componente componente = evt.getTarget();

		KnowledgeBase knowledgeBase = FormworkContext.getKnowledgeBase();
		
		StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
		
		session.insert(componente);
		session.fireAllRules();
	}

}
