package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Board;

public interface BoardService {

	
	/**
	 * 리스트(글 목록)를 가져온다.
	 * @return list;
	 */
	public List<Board> getList();
	
	/**
	 * 
	 * @param req - no 를 받아 해당 글을 출력한다.
	 * @return view - 해당 글 번호의 내용을 view에 담아 내보낸다.
	 */
	public Board getBoardno(HttpServletRequest req);
	
	
	public Board view(Board b);
}
