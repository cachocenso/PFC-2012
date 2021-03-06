package edu.uoc.pfc.formwork.ui;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Clase que realiza el renderizado de un árbol de componentes
 * a código HTML + JS.
 * @author Alberto Díaz en 16/12/2012
 */
public class HTMLRenderer implements IRenderer {

	private static Logger logger = Logger.getLogger(HTMLRenderer.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.uoc.pfc.formwork.ui.IRenderer#render(edu.uoc.pfc.formwork.ui.Componente
	 * )
	 */
	public void render(Componente root, Writer out) {

		if (!(root instanceof Formulario)) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto Formulario");
		}

		
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		configuration.setClassForTemplateLoading(getClass(), "/");

		try {
			Template template = configuration
					.getTemplate("templates/formwork.ftl");

			Map<String, Componente> dataModel = new HashMap<String, Componente>();

			dataModel.put("form", root);
			template.process(dataModel, out);
		} catch (IOException e) {
			logger.fatal("No se encuentra la plantilla", e);
		} catch (TemplateException e) {
			logger.fatal("Error haciendo rendering", e);
		}
	}

}
