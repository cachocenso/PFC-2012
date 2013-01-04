package edu.uoc.pfc.formworktest.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DummyPresentationServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/plain");
		if (Math.random() > 0.3) {
			// Simulamos una presentación correcta.
			// Creamos un número de justificante para la declaración
			// presentada.
			Date now = new Date();
			
			String sNow = Long.toString(now.getTime());
			
			resp.getWriter().print(String.format("666%s", sNow.substring(sNow.length() - 10)));
		}
		else {
			resp.getWriter().print("ERROR");
		}
	}
}
