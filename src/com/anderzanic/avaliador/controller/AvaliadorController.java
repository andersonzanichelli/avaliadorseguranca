package com.anderzanic.avaliador.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anderzanic.avaliador.service.AvaliadorService;

/**
 * Servlet implementation class AvaliadorController
 */
@WebServlet("/avaliadorController")
public class AvaliadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AvaliadorController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AvaliadorService service = new AvaliadorService();
		
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println(service.avaliar(password));
		out.close();
	}

}
