package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 파라미터를 전달하여 Paging 객체 생성하기
		Paging paging = boardService.getPaging(req);
		
		System.out.println("BoardListController - " + paging);
		
		// List<Board> boardList = boardService.getList();
		List<Board> boardList = boardService.getList(paging);
		
		req.setAttribute("paging", paging);
		
		// System.out.println(boardList);
		
		req.setAttribute("BoardList", boardList);
	
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
		
	}
	
	
}
