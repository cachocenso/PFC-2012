/**
 * 
 */
package edu.uoc.pfc.formwork.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import edu.uoc.pfc.formwork.infraestructura.Attributes;
import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import edu.uoc.pfc.formwork.infraestructura.annotation.Session;
import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.ui.Partida;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Clase abstracta de la que derivan todos los controloladores de aplicaciones
 * Formwork.
 * 
 * @author Alberto Díaz en 22/11/2012
 */
public abstract class GenericController implements IController {

	@Session
	protected HttpSession session;

	public void onPresentar() {
		// Obtenemos y configuramos la base de conocimientos
		KnowledgeBase knowledgeBase = ((FormworkContext) session
				.getServletContext().getAttribute(Attributes.FWCONTEXT))
				.getKnowledgeBase();
		
		if (knowledgeBase != null) {
			StatelessKnowledgeSession ksession = knowledgeBase
					.newStatelessKnowledgeSession();

			
			Formulario theForm = (Formulario) session.getAttribute(Attributes.FWCOMPONENTS);
			
			// Inicializamos las listas de mensajes de error y de partidas afectadas
			// y las insertamos en el �rea de trabajo como variables globales.
			List<Partida<?>> partidasAfectadas = getListaPartidas();

			List<Mensaje> listaErrores = getListaMensajes();

			ksession.setGlobal("partidasAfectadas", partidasAfectadas);
			ksession.setGlobal("errores", listaErrores);
			
			// Insertamos todo el formulario para las validaciones globales
			ksession.execute(theForm);
			
			if (listaErrores.size() == 0) {
				// Obtener el registro
				
				// Realizar la presenteción
			}
		}
	}

	public abstract void onEvent(FormworkEvent ev);

	/**
	 * @return
	 */
	protected List<Mensaje> getListaMensajes() {
		List<Mensaje> listaErrores = (List<Mensaje>) session
				.getAttribute(Attributes.FWLISTAERRORES);
		if (listaErrores == null) {
			listaErrores = new ArrayList<Mensaje>();
			session.setAttribute(Attributes.FWLISTAERRORES, listaErrores);
		}
		return listaErrores;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<Partida<?>> getListaPartidas() {
		List<Partida<?>> partidasAfectadas = (List<Partida<?>>) session
				.getAttribute(Attributes.FWLISTAPARTIDAS);
	
		if (partidasAfectadas == null) {
			partidasAfectadas = new ArrayList<Partida<?>>();
			session.setAttribute(Attributes.FWLISTAPARTIDAS, partidasAfectadas);
		}
		return partidasAfectadas;
	}
}
