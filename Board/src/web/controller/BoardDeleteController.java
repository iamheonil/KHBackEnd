package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dao.face.BoardDao;
import web.dao.face.BoardFileDao;
import web.dao.impl.BoardDaoImpl;
import web.dao.impl.BoardFileDaoImpl;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	private BoardFileDao boardFileDao = new BoardFileDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// System.out.println("DELETE!");
		
		Board board = boardService.getBoardno(req);
		BoardFile boardFile = boardFileDao.getBoardno(req);

		boardFileDao.delete(boardFile);
		boardService.delete(board);

		req.getRequestDispatcher("/board/list").forward(req, resp);

	}

}
