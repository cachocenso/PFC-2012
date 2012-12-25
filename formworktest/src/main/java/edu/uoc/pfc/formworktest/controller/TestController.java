/**
 * 
 */
package edu.uoc.pfc.formworktest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import edu.uoc.pfc.formwork.infraestructura.Attributes;
import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import edu.uoc.pfc.formwork.infraestructura.annotation.Session;
import edu.uoc.pfc.formwork.service.GenericController;
import edu.uoc.pfc.formwork.service.Mensaje;
import edu.uoc.pfc.formwork.ui.Partida;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Controlador de la aplicación de ejemplo del framwork.
 * 
 * @author Alberto Díaz en 23/11/2012
 */
public class TestController extends GenericController {
	private static Logger logger = Logger.getLogger(TestController.class);

	@Session
	private HttpSession session;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.uoc.pfc.formwork.ui.GenericController#onEvent(edu.uoc.pfc.formwork
	 * .ui.event.FormworkEvent)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onEvent(FormworkEvent evt) {
		
		// Se extrae la partida y se le asigna el nuevo valoir
		Partida componente = (Partida) evt.getTarget();
		componente.setValue(evt.getValue());
		
		// Obtenemos y configuramos la base de conocimientos
		KnowledgeBase knowledgeBase = ((FormworkContext) session
				.getServletContext().getAttribute(Attributes.FWCONTEXT))
				.getKnowledgeBase();

		// Creamos una sesi�n de DROOLS y establecemos el �rbol de componentes como
		// variable global
		StatelessKnowledgeSession ksession = knowledgeBase
				.newStatelessKnowledgeSession();

		
		ksession.setGlobal("theForm",
				session.getAttribute(Attributes.FWCOMPONENTS));

		// Inicializamos las listas de mensajes de error y de partidas afectadas
		// y las insertamos en el �rea de trabajo como variables globales.
		List partidasAfectadas = (List) session
				.getAttribute(Attributes.FWLISTAPARTIDAS);

		if (partidasAfectadas == null) {
			partidasAfectadas = new ArrayList<Object>();
			session.setAttribute(Attributes.FWLISTAPARTIDAS, partidasAfectadas);
		}

		List<Mensaje> listaErrores = (List<Mensaje>) session
				.getAttribute(Attributes.FWLISTAERRORES);
		if (listaErrores == null) {
			listaErrores = new ArrayList<Mensaje>();
			session.setAttribute(Attributes.FWLISTAERRORES, listaErrores);
		}

		ksession.setGlobal("partidasAfectadas", partidasAfectadas);
		ksession.setGlobal("errores", listaErrores);

		logger.info("Antes de aplicar las reglas: " + componente.getValue());

		// Ejecutamos las reglas asociadas al componente cuyo valor ha cambiado.
		// Al regresar de la ejecuci�n de las reglas, una de las dos listas tendr� contenido.
		ksession.execute(componente);
		logger.info("Después de aplicar las reglas: " + componente.getValue());
	}

}
