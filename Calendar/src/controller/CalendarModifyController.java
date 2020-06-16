package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.CalendarService;
import service.impl.CalendarServiceImpl;

/**
 * Servlet implementation class CalendarModifyController
 */
@WebServlet("/calendar/modify")
public class CalendarModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CalendarService calendarService = new CalendarServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/calendarmodify.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
	}

}
