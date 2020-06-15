package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.dto.Member;
import web.service.face.BoardService;
import web.service.face.MemberService;
import web.service.impl.BoardServiceImpl;
import web.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class BoardWriteController
 */
@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();
	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Member member = memberService.getLoginMember(req);
//		
//		member = memberService.info(member);
//		
//		HttpSession session = req.getSession();
//		session.setAttribute("writer", member.getUsernick());

		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Board param = boardService.getBoard(req);

//		int testNum = boardDao.selectBoardno();

		System.out.println(param);
//		System.out.println(testNum);

		// boardService.write(param);
		// boardService.write(req);
		
		boardService.write(req, resp);

		resp.sendRedirect("/board/list");

	}

}
