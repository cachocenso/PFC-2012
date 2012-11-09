package edu.uoc.pfc.formwork.infraestructura;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.drools.KnowledgeBase;

public class FormworkListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		

	}

	public void contextInitialized(ServletContextEvent event) {
		// Cargar la configurción del framework.
		loadFrameWorkConfig();
		
		// Obtener el ServletContext
		ServletContext servletContext = event.getServletContext();
		
		// Crear contexto
		FormworkContext formworkContext = new FormworkContext();
		
		// Cargar fichero de reglas.
		KnowledgeBase base = createKnowledgeBase();
		
		// Almacenar KnowledgeBase en contexto.
		formworkContext.setKnowledgeBase(base);
		
		// Almacenar contexto en ServletContext.
		servletContext.setAttribute(Attributes.FWCONTEXT, formworkContext);
	}

	private void loadFrameWorkConfig() {
		// Cargar el fichero XML de configuración.
		
		
	}

	private KnowledgeBase createKnowledgeBase() {
		// TODO Auto-generated method stub
		return null;
	}

}
