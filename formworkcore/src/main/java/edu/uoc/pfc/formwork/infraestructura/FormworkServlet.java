package edu.uoc.pfc.formwork.infraestructura;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.google.gson.Gson;

import edu.uoc.pfc.formwork.infraestructura.ResponseJson.Resultado;
import edu.uoc.pfc.formwork.infraestructura.annotation.Session;
import edu.uoc.pfc.formwork.service.IController;
import edu.uoc.pfc.formwork.service.Mensaje;
import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.ui.HTMLRenderer;
import edu.uoc.pfc.formwork.ui.IRenderer;
import edu.uoc.pfc.formwork.ui.Partida;
import edu.uoc.pfc.formwork.ui.event.FormworkEvent;
import edu.uoc.pfc.formwork.xml.TipoFW;
import edu.uoc.pfc.formwork.xml.TipoFormulario;
import edu.uoc.pfc.formwork.xml.XMLLoader;

/**
 * Servlet central del framework. Recibe y trata todas las peticiones, AJAX o de
 * carga de recursos, que se le hagan desde el cliente.
 * 
 * 
 * @author Alberto Díaz en 05/12/2012
 * 
 */
@SuppressWarnings("serial")
public class FormworkServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(FormworkServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {

		String uri = req.getRequestURI();
		logger.info("URI recibida: " + uri);
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

		if (uri.indexOf("~/") != -1) {
			// Se está solicitando un recurso
			processResourceRequest(uri.substring(uri.indexOf("~/") + 1), resp);
		} else if (uri.indexOf("update") != -1) {
			// Se trata de una petición de actualización AJAX de la aplicación.
			processAjaxRequests(req, resp);
		} else if (uri.indexOf("submit") != -1) {
			// Se trata de una petición de presentación de la declaracion.
			processSubmitRequest(req, resp);
		}

	}

	/**
	 * Procesa una petición de submit del formulario.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void processSubmitRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();

		IController controller = (IController) session
				.getAttribute(Attributes.FWCONTROLLER);

		controller.onPresentar();

		// Recojo la lista de los posibles errores.
		@SuppressWarnings("unchecked")
		List<Mensaje> errores = (List<Mensaje>) session
				.getAttribute(Attributes.FWLISTAERRORES);
		
		ResponseJson json = new ResponseJson();
		if (errores.size() == 0) {			
			json.setResultado(Resultado.SUCCESS);
			json.setResponseObjects(Arrays.asList(controller.getRegistro()));
		}
		else {
			
		}
		
		// Devolvemos la respuesta.
		String serializedResponse = new Gson().toJson(json);
		resp.setContentType("application/json");
		resp.getWriter().print(serializedResponse);		
	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void processAjaxRequests(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		fireBusinessRules(req);

		renderResponse(req, resp);
	}

	/**
	 * Este método implementa el caso de uso Fire business rules. Se ejecutan
	 * las reglas de negocio.
	 * 
	 * @param req
	 * @param controller
	 * @param event
	 * @param partida
	 */
	private void fireBusinessRules(HttpServletRequest req) {
		HttpSession session = req.getSession();

		IController controller = (IController) session
				.getAttribute(Attributes.FWCONTROLLER);

		if (controller != null) {
			FormworkEvent event = new FormworkEvent();
			Formulario formulario = (Formulario) session
					.getAttribute(Attributes.FWCOMPONENTS);
			Partida<?> partida = formulario.getPartida(req.getParameter("id"));
			event.setTarget(partida);
			event.setOldValue(partida.getValue());
			event.setNewValue(req.getParameter("value"));
			controller.onEvent(event);
		}
	}

	/**
	 * Este método implementa el caso de uso Render response. Se recoge el
	 * resultado de la ejecución de las reglas de negocio y se envía la
	 * respuesta al cliente en el formato apropiado.
	 * 
	 * @param req
	 * @param rep
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void renderResponse(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();

		// Recojo la lista de los posibles errores.
		List<Mensaje> errores = (List<Mensaje>) session
				.getAttribute(Attributes.FWLISTAERRORES);

		Gson gson = new Gson();
		String serializedResponse;

		ResponseJson responseJson = new ResponseJson();

		if (errores.size() > 0) {
			responseJson.setResultado(Resultado.ERROR);
			responseJson.setResponseObjects(errores);
		} else {
			// No hubo errores. Recojo la lista de partidas afectadas.
			List<?> partidasAfectadas = (List<?>) session
					.getAttribute(Attributes.FWLISTAPARTIDAS);
			responseJson.setResultado(Resultado.SUCCESS);
			responseJson.setResponseObjects(partidasAfectadas);
		}

		// Devolvemos la respuesta.
		serializedResponse = gson.toJson(responseJson);
		resp.setContentType("application/json");
		resp.getWriter().print(serializedResponse);
	}

	/**
	 * Procesa una petición de carga de recursos.
	 * 
	 * @param uri
	 * @param resp
	 * @throws IOException
	 */
	private void processResourceRequest(String uri, HttpServletResponse resp)
			throws IOException {
		logger.info("Recurso solicitado: " + uri);
		InputStream resourceStream = getClass().getResourceAsStream(uri);
		logger.info("Input stream " + resourceStream);
		// El navegador Opera no interpreta bien los recursos
		// si la respuesta no lleva el tipO MIME adecuado.
		if (uri.endsWith(".css")) {
			resp.setContentType("text/css");
		} else if (uri.endsWith(".js")) {
			resp.setContentType("text/javascript");
		}
		ServletOutputStream respStream = resp.getOutputStream();
		IOUtils.copy(resourceStream, respStream);

	}

	/**
	 * Realiza la carga de la página fwp de la aplicación, la analiza y
	 * construye el árbol de componentes. Instancia el controlador y después se
	 * renderiza a HTML y devuelve al cliente el árbol de componentes
	 * renderizado.
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
			TipoFormulario tipoFormulario = XMLLoader.parseFile(
					TipoFormulario.class, resource);
			Formulario theForm = ComponentTreeFactory.createComponentsTree(
					tipoFormulario, (TipoFW) req.getServletContext()
							.getAttribute(Attributes.FWCONFIG));

			HttpSession session = req.getSession(true);
			session.setAttribute(Attributes.FWCOMPONENTS, theForm);

			logger.info("Árbol de componentes cargado.");

			setupController(theForm, session);
			logger.info("Controlador iniciado:"
					+ theForm.getNombreControlador());

			doRender(resp, theForm);

		} catch (JAXBException e) {
			logger.error("Error cargando página.", e);
		} catch (SAXException e) {
			logger.error("Error cargando página.", e);
		}

	}

	/**
	 * Se realiza el renderizado del árbol de componentes a HTML
	 * 
	 * @param resp
	 * @param theForm
	 * @throws IOException
	 */
	private void doRender(HttpServletResponse resp, Formulario theForm)
			throws IOException {
		IRenderer renderer = new HTMLRenderer();
		resp.setCharacterEncoding("UTF-8");
		Writer writer = resp.getWriter();

		renderer.render(theForm, writer);

		logger.info("Pagina renderizada.");
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

		// inyectamos la sessión la sesión.
		injectSession(session, controller);

		// Guardo el controlador en la sesión.
		session.setAttribute(Attributes.FWCONTROLLER, controller);
	}

	/**
	 * Busca en la clase controller recibida como parámetro un attributo con la
	 * anotación
	 * 
	 * @see Session. Si está presente, le inyecta la sesión session que se
	 *      recibe como parámetro.
	 * 
	 * @param session
	 * @param controller
	 * @throws IllegalAccessException
	 */
	private void injectSession(HttpSession session, IController controller)
			throws IllegalAccessException {

		List<Field> fields = getAllFields(controller.getClass());

		for (Field field : fields) {
			if (field.isAnnotationPresent(Session.class)
					&& field.getType().isAssignableFrom(session.getClass())) {
				field.setAccessible(true);
				field.set(controller, session);
			}
		}
	}

	/**
	 * Mediante este sencillo método recursivo, devolvemos todos los atributos
	 * de la clase recibida como parámetro y de todas sus superclases.
	 * 
	 * @param type
	 * @return la lista de atributos de la clase.
	 */
	public List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();

		if (type.getSuperclass() != null) {
			fields.addAll(getAllFields(type.getSuperclass()));
		}

		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		return fields;
	}

}
