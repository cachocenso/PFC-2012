/**
 * 
 */
package edu.uoc.pfc.formworktest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
//import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;

import edu.uoc.pfc.formwork.infraestructura.Attributes;
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
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void onEvent(FormworkEvent evt) {
		Partida componente = (Partida) evt.getTarget();
		
		componente.setValue( evt.getValue());
		KnowledgeBase knowledgeBase = FormworkContext.getKnowledgeBase();
		
		
		StatelessKnowledgeSession ksession = knowledgeBase.newStatelessKnowledgeSession();
		
		ksession.setGlobal("theForm", session.getAttribute(Attributes.FWCOMPONENTS));

		List partidasAfectadas = (List) session.getAttribute(Attributes.FWLISTAPARTIDAS);
		
		if (partidasAfectadas == null) {
			partidasAfectadas = new ArrayList<String>();
			session.setAttribute(Attributes.FWLISTAPARTIDAS, partidasAfectadas);
		}
		
		partidasAfectadas.clear();
		ksession.setGlobal("partidasAfectadas", partidasAfectadas);
		logger.info("Antes de aplicar las reglas: " + componente.getValue());
		
		ksession.execute(componente);
		logger.info("Después de aplicar las reglas: " + componente.getValue());
	}

}
