/**
 * 
 */
package edu.uoc.pfc.formwork.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.google.gson.Gson;

import edu.uoc.pfc.formwork.infraestructura.Attributes;
import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import edu.uoc.pfc.formwork.infraestructura.annotation.Session;
import edu.uoc.pfc.formwork.ui.Apartado;
import edu.uoc.pfc.formwork.ui.Componente;
import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.ui.Partida;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;

/**
 * Clase abstracta de la que pueden derivar 
 * los controloladores de aplicaciones Formwork.
 * 
 * @author Alberto Díaz en 22/11/2012
 */
public abstract class GenericController implements IController {

	@Session
	protected HttpSession session;

	/**
	 * Representa un campo del registro de salida
	 * que se envía al cliente para que lo firme.
	 * 
	 * @author albertodiaz
	 *
	 */
	public class Campo {
		private String id;
		private Object valor;
		
		public Object getValor() {
			return valor;
		}
		public void setValor(Object valor) {
			this.valor = valor;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	}
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
		}
	}

	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.service.IController#getRegistro()
	 */
	public String getRegistro() {
		Formulario theForm = (Formulario) session.getAttribute(Attributes.FWCOMPONENTS);
		
		List<Campo> campos = new ArrayList<Campo>();
		
		for (Apartado apartado : theForm.getApartados()) {
			for (Componente comp : apartado.getContenido()) {
				if (comp instanceof Partida<?>) {
					Campo campo = new Campo();
					campo.setId(comp.getId());
					campo.setValor(((Partida<?>) comp).getValue());
					campos.add(campo);
				}
			}
		}
		return new Gson().toJson(campos);
	}
	
	public abstract void onEvent(FormworkEvent ev);

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
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
