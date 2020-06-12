package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class MemberLoginController
 */

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Member member = memberService.getLoginMember(req);
		
		req.setAttribute("memberView", member);
		
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
		
		
	}

}
