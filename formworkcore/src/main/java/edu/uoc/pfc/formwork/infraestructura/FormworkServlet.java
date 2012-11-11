package edu.uoc.pfc.formwork.infraestructura;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import edu.uoc.pfc.formwork.ui.Apartado;
import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.xml.TipoApartado;
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
	
	private void load(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException {
		logger.info("inicio de la carga de la página");
		
		String uri = req.getRequestURI();
		InputStream resource = getServletContext().getResourceAsStream(uri.substring(uri.lastIndexOf('/')));
		
		try {
			TipoFormulario formulario = XMLLoader.parseFile(TipoFormulario.class, resource);
			Formulario theForm = ComponentTreeFactory.createComponentsTree(formulario);
			req.getSession(true).setAttribute(Attributes.FWCOMPONENTS, theForm);
			logger.info("Árbol de componentes cargado.");
		} catch (JAXBException e) {
			logger.error("Error cargando página.", e);
		} catch (SAXException e) {
			logger.error("Error cargando página.", e);
		}
		
		

	}


}
