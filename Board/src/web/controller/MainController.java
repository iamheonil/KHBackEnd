package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 세션 객체 생성
		HttpSession session = req.getSession();

		// 세션 정보 저장
		session.setAttribute("userid", "writer1");
		session.setAttribute("userpw", "writer11");
		session.setAttribute("usernick", "writer111");
		
		
		req.setAttribute("userid", "writer1");
		req.setAttribute("userpw", "writer11");
		req.setAttribute("usernick", "writer111");
		
		// 세션 유지시간 변경하기
		session.setMaxInactiveInterval(0); // 0 은 무한
//		session.setMaxInactiveInterval(5); // 5초
//		session.setMaxInactiveInterval(1 * 60 * 60); // 1시간

		// View 지정하기
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
		
		
	}

}
