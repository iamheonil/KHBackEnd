package web.dao.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.BoardFile;

public interface BoardFileDao {
	
	/**
	 * 첨부파일 삭제할 게시글 번호를 받아온다.
	 * @param req
	 * @return
	 */
	public BoardFile getBoardno(HttpServletRequest req);

	/**
	 * 첨부파일을 삭제한다.
	 * @param board
	 */
	public void delete(BoardFile board);

	
}
