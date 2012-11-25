/**
 * 
 */
package edu.uoc.pfc.formworktest.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import edu.uoc.pfc.formwork.infraestructura.Session;
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
	private static Logger logger = Logger.getLogger(TestController.class);
	
	@Session
	private HttpSession session;
	
	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.GenericController#onEvent(edu.uoc.pfc.formwork.ui.event.FormworkEvent)
	 */
	public void onEvent(FormworkEvent evt) {
		Partida componente = (Partida) evt.getTarget();
		
		componente.setValue( evt.getValue());
		KnowledgeBase knowledgeBase = FormworkContext.getKnowledgeBase();
		
		StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
//		StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();
		
		
		logger.info("Antes de aplicar las reglas: " + componente.getValue());
		
		ksession.insert(componente);
		ksession.fireAllRules();
		logger.info("Después de aplicar las reglas: " + componente.getValue());
	}

}
