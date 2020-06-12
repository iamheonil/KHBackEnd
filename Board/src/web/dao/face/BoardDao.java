package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.util.Paging;

public interface BoardDao {

	/**
	 * 글 전체목록 출력해주는 메소드
	 * 
	 * @return
	 */
	public List<Board> selectAll();

	/**
	 * 게시글 상세내용 보여주는 메소드
	 * 
	 * @param no
	 * @return
	 */
	public Board selectBoardByBoardno(Board boardno);

	/**
	 * 게시글 조회수에 대한 메소드
	 * 
	 * @param b
	 */
	public void updateHit(Board board);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @return - 전체 게시글 수
	 */
	public int selectCntAll();

	/**
	 * 글 전체목록 출력해주는 메소드
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return list - 페이징 적용한 전체 글 목록 출력 해주는 Method
	 */
	public List<Board> selectAll(Paging paging);

}
