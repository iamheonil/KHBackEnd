package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.service.face.BoardService;
import web.util.Paging;

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

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달파라미터 curPage를 파싱한다.
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntAll();

		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);

		// 계산된 Paging 객체 반환
		return paging;
	}

	@Override
	public List<Board> getList(Paging paging) {

		return boardDao.selectAll(paging);
	}

}
