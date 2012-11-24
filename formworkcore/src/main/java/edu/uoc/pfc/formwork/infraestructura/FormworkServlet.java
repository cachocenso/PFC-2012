package edu.uoc.pfc.formwork.infraestructura;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedReader;
import java.io.Writer;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.ui.HTMLRenderer;
import edu.uoc.pfc.formwork.ui.IController;
import edu.uoc.pfc.formwork.ui.IRenderer;
import edu.uoc.pfc.formwork.xml.TipoFormulario;
import edu.uoc.pfc.formwork.xml.XMLLoader;

@SuppressWarnings("serial")
public class FormworkServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(FormworkServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {

		String uri = req.getRequestURI();

		try {
			if (uri.indexOf("/au/") != -1) {
				processAuRequest(req, resp);
			} else {
				load(req, resp);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Procesa las peticiones AJAX.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void processAuRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		String uri = req.getRequestURI();

		int index = uri.indexOf("~/");
		if (index != -1) {
			// Se está solicitando un recurso
			processResourceRequest(uri.substring(index + 1), resp);
		} else {
			// Se trata de una petición de actualización AJAX de la aplicación.
			processAjaxRequests(req, resp);
		}

	}

	/**
	 * @param req
	 * @param resp
	 */
	private void processAjaxRequests(HttpServletRequest req,
			HttpServletResponse resp) {

	}

	/**
	 * @param uri
	 * @param resp
	 * @throws IOException
	 */
	private void processResourceRequest(String uri, HttpServletResponse resp)
			throws IOException {
		logger.info("Recurso solicitado: " + uri);
		InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(uri);
		ServletOutputStream respStream = resp.getOutputStream();
		IOUtils.copy(resourceStream,
				respStream);

	}

	/**
	 * Realiza la carga de la página fwp de la aplicación, la analiza y
	 * construye el árbol de componentes. Después se renderiza a HTML y se
	 * devuelve al cliente.
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void load(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		logger.info("inicio de la carga de la página");

		String uri = req.getRequestURI();
		InputStream resource = getServletContext().getResourceAsStream(
				uri.substring(uri.lastIndexOf('/')));

		try {
			TipoFormulario formulario = XMLLoader.parseFile(
					TipoFormulario.class, resource);
			Formulario theForm = ComponentTreeFactory
					.createComponentsTree(formulario);

			HttpSession session = req.getSession(true);
			session.setAttribute(Attributes.FWCOMPONENTS, theForm);

			logger.info("Árbol de componentes cargado.");
			

			setupController(theForm, session);
			logger.info("Controlador iniciado:" + theForm.getNombreControlador());


			IRenderer renderer = new HTMLRenderer();
			Writer writer = resp.getWriter();

			renderer.render(theForm, writer);

			logger.info("Pagina renderizada.");

		} catch (JAXBException e) {
			logger.error("Error cargando página.", e);
		} catch (SAXException e) {
			logger.error("Error cargando página.", e);
		}

	}

	/**
	 * Instancia el objeto controlador definida en la página de la aplicación,
	 * inyecta el objeto sesión y lo almacena en la misma sesión.
	 * 
	 * @param theForm
	 * @param session
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private void setupController(Formulario theForm, HttpSession session)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		// Instancio un objeto de la clase controladora indicada
		// en la página principal de la aplicación.
		IController controller = (IController) Class.forName(
				theForm.getNombreControlador()).newInstance();

		// Busco en el objeto controller un atributo de tipo HttpSession
		// que esté anotado con Session y le inyecto la sesión.
		Field[] fields = controller.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(Session.class)
					&& field.getType().isAssignableFrom(session.getClass())) {
				field.setAccessible(true);
				field.set(controller, session);
			}
		}

		// Guardo el controlador en la sesión.
		session.setAttribute(Attributes.FWCONTROLLER, controller);
	}

}
