package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDao boardDao = new BoardDaoImpl();
	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Board boardno = boardService.getBoardno(req);

		// System.out.println(boardno);

		// 상세보기 결과 조회
		Board viewBoard = boardService.view(boardno);

		// System.out.println(viewBoard);

		// 첨부파일 정보 VIEW에 전달
		// BoardFile boardFile = boardService.viewFile(viewBoard);
		// req.setAttribute("boardFile", boardFile);

		// 닉네임 전달
		// req.setAttribute("nick", boardService.getNick(viewBoard));

		// 조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
		// 파일 조회 결과 값 전달
		// req.setAttribute("boardFile", boardFile);
	
		// System.out.println(boardFile);
		// System.out.println(viewBoard);

		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Board board = new Board();
		
		boardService.update(board, req);
		
		resp.sendRedirect("/board/list");
		
	}

}
