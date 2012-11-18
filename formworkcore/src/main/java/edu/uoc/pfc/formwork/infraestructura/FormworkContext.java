package edu.uoc.pfc.formwork.infraestructura;

import org.drools.KnowledgeBase;

import edu.uoc.pfc.formwork.ui.IRenderer;
import freemarker.template.Configuration;

/**
 * Esta clase es un contenedor para los objetos
 * necesarios para el correcto funcionamiento del
 * framework y a los que se debe de acceder desde 
 * cualquier parte de la aplicación.
 * 
 * @author Alberto Díaz. 
 *
 */
public class FormworkContext {

	/**
	 * Base de conocimiento de DROOLS
	 */
	private static KnowledgeBase knowledgeBase;
	/**
	 * Renderer de la página de la aplicación
	 */
	private static IRenderer renderer;
	private static Configuration configuration;


	/**
	 * Contructor privado para una utility class
	 */
	private FormworkContext() {
		// TODO Auto-generated constructor stub
	}
	
	/*-- GETTERS AND SETTERS --*/
	public static KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public static void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		FormworkContext.knowledgeBase = knowledgeBase;
	}

	public IRenderer getRenderer() {
		return renderer;
	}
	
	public static void setRenderer(IRenderer renderer) {
		FormworkContext.renderer = renderer;
		
		
	}

	/**
	 * setter para el objeto Configuration de freemarker
	 * @param configuration
	 */
	public static void setTemplateConfig(Configuration configuration) {
		FormworkContext.configuration = configuration;

	}

	/**
	 * @return the configuration
	 */
	public static Configuration getTemplateConfig() {
		return configuration;
	}


}

