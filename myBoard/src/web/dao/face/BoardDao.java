package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.dto.BoardFile;
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

	/**
	 * 게시글 인서트
	 * 
	 * @param b - 요청 객체 정보
	 */
	public void insert(Board b);

	/**
	 * 파일 업로드에 필요한 BoardNo Select
	 * 
	 * @return
	 */
	public int selectBoardno();

	/**
	 * 업로드 된 파일의 정보들을 기록하는 메소드
	 * 
	 * @param boardFile
	 */
	public void insertFile(BoardFile boardFile);
	
	
	/**
	 * 첨부파일의 정보를 가져온다!
	 * @param viewBoard
	 * @return
	 */
	public BoardFile selectFile(Board viewBoard);
	
	
	/**
	 * 파일과 첨부파일을 삭제한다.
	 * 
	 * @param board - 요청 객체 정보 
	 */
	public void delete(Board board);

}
