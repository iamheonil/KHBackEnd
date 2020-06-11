package web.dao.face;

import java.util.List;

import web.dto.Board;

public interface BoardDao {

	/**
	 * 글 전체목록 출력해주는 메소드
	 * @return
	 */
	public List<Board> selectAll();
	
	/**
	 * 게시글 상세내용 보여주는 메소드
	 * @param no 
	 * @return
	 */
	public Board selectBoardByBoardno(int no);
	
	/**
	 * 게시글 추천수에 대한 메소드
	 * @param b
	 */
	public void updateHit(Board b);
	
}
