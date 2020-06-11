package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.service.face.BoardService;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	public List<Board> getList() {

		List<Board> boardList = boardDao.selectAll();

		return boardList;
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {
		
		
		String sno = req.getParameter("boardno");
		int no = Integer.parseInt(sno);
		
		Board board = boardDao.selectBoardByBoardno(no);
		
		return board;
	}

	@Override
	public Board view(Board b) {
		// TODO Auto-generated method stub
		return null;
	}


}
