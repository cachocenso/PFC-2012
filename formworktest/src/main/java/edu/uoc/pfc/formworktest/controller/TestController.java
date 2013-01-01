/**
 * 
 */
package edu.uoc.pfc.formworktest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import edu.uoc.pfc.formwork.infraestructura.Attributes;
import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import edu.uoc.pfc.formwork.service.GenericController;
import edu.uoc.pfc.formwork.service.Mensaje;
import edu.uoc.pfc.formwork.ui.Partida;
import edu.uoc.pfc.formwork.ui.UIUtils;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Controlador de la aplicación de ejemplo del framwork.
 * 
 * @author Alberto Díaz en 23/11/2012
 */
public class TestController extends GenericController {
	private static Logger logger = Logger.getLogger(TestController.class);


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.uoc.pfc.formwork.ui.GenericController#onEvent(edu.uoc.pfc.formwork
	 * .ui.event.FormworkEvent)
	 */
	@SuppressWarnings({ "rawtypes"})
	public void onEvent(FormworkEvent evt) {
		
		// Se extrae la partida y se le asigna el nuevo valoir
		Partida partida = (Partida) evt.getTarget();
		try {
			UIUtils.setValue(partida, evt.getNewValue());
		} catch (Exception e) {
			logger.error("Valor err�neo para componente " + partida, e);
			return;
		} 
		
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
		List<Partida<?>> partidasAfectadas = getListaPartidas();

		List<Mensaje> listaErrores = getListaMensajes();

		ksession.setGlobal("partidasAfectadas", partidasAfectadas);
		ksession.setGlobal("errores", listaErrores);

		logger.info("Antes de aplicar las reglas: " + partida.getValue());

		// Ejecutamos las reglas asociadas al componente cuyo valor ha cambiado.
		// Al regresar de la ejecuci�n de las reglas, una de las dos listas tendr� contenido.
		ksession.execute(partida);
		logger.info("Después de aplicar las reglas: " + partida.getValue());
	}


	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.service.IController#prepararRegistro()
	 */
	public String prepararRegistro() {
		// TODO Auto-generated method stub
		return "";
	}

}
