package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.PlanService;
import service.impl.PlanServiceImpl;

/**
 * Servlet implementation class CalendarModifyController
 */
@WebServlet("/calendar/modify")
public class PlanModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanService calendarService = new PlanServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/calendarmodify.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
	}

}
