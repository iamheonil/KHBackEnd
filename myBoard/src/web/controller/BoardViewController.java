package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

/**
 * Servlet implementation class BoardViewController
 */
@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달파라미터 얻기 - boardno
		Board boardno = boardService.getBoardno(req);

		// 상세보기 결과 조회
		Board viewBoard = boardService.view(boardno);
		
		BoardFile boardFile = boardService.viewFile(viewBoard);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		req.setAttribute("boardFile", boardFile);

		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);

	}

}
