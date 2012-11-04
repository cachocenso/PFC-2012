package edu.uoc.pfc.formwork.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import edu.uoc.pfc.formwork.xml.TipoFormulario;
import edu.uoc.pfc.formwork.xml.XMLLoader;

@SuppressWarnings("serial")
public class FormworkLoaderServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		load(req, resp);
	}
	
	private void load(HttpServletRequest req, HttpServletResponse resp) {
		InputStream resource = getServletContext().getResourceAsStream("/index.fwp");
		
		try {
			TipoFormulario formulario = XMLLoader.parseFwp(TipoFormulario.class, resource);
			System.out.println("formulario: " + formulario);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
