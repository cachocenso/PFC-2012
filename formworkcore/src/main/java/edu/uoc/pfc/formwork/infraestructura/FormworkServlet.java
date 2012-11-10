package edu.uoc.pfc.formwork.infraestructura;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;


import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.xml.TipoFormulario;
import edu.uoc.pfc.formwork.xml.XMLLoader;

@SuppressWarnings("serial")
public class FormworkServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(FormworkServlet.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		load(req, resp);
	}
	
	private void load(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("inicio de la carga de la página");
		
		InputStream resource = getServletContext().getResourceAsStream("/index.fwp");
		
		try {
			TipoFormulario formulario = XMLLoader.parseFile(TipoFormulario.class, resource);
			createComponentsTree(formulario);
		} catch (JAXBException e) {
			logger.error("Error cargando página.", e);
		} catch (SAXException e) {
			logger.error("Error cargando página.", e);
		}

	}

	private void createComponentsTree(TipoFormulario formulario) {
		Formulario theForm = new Formulario();
		
		
	}
}
