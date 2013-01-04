package edu.uoc.pfc.formworktest.servlet;

import java.io.IOException;

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
			resp.getWriter().print("SUCCESS");
		}
		else {
			resp.getWriter().print("ERROR");
		}
	}
}
