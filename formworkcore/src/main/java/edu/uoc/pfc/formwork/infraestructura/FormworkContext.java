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
	private KnowledgeBase knowledgeBase;
	/**
	 * Renderer de la página de la aplicación
	 */
	private IRenderer renderer;
	private Configuration configuration;


	
	/*-- GETTERS AND SETTERS --*/
	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public IRenderer getRenderer() {
		return renderer;
	}
	
	public void setRenderer(IRenderer renderer) {
		this.renderer = renderer;
		
		
	}

	/**
	 * setter para el objeto Configuration de freemarker
	 * @param configuration
	 */
	public void setTemplateConfig(Configuration configuration) {
		this.configuration = configuration;

	}

	/**
	 * @return the configuration
	 */
	public Configuration getTemplateConfig() {
		return configuration;
	}


}

