package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.dto.BoardFile;
import web.util.Paging;

public interface BoardDao {

	/**
	 * Board테이블 전체 조회
	 * 
	 * @return List<Board> - Board테이블 전체 조회 결과 리스트
	 */
	public List<Board> selectAll();

	/**
	 * 총 게시글 수 조회
	 * 
	 * @return
	 */
	public int selectCntAll();

	/**
	 * 페이징 대상 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 조회된 게시글 목록
	 */
	public List<Board> selectAll(Paging paging);

	/**
	 * 특정 게시글 조회
	 * 
	 * @param boardno - 조회할 boardno를 가진 객체
	 * @return Board - 조회된 결과 객체
	 */
	public Board selectBoardByBoardno(Board boardno);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param boardno - 조회된 게시글 번호를 가진 객체
	 */
	public void updateHit(Board boardno);

	/**
	 * 게시글 입력
	 * 
	 * @param board - 삽입될 게시글 내용
	 */
	public void insert(Board board);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserid(Board viewBoard);

	/**
	 * 다음 게시글 번호 반환 게시글 테이블과 첨부파일 테이블에 입력될 게시글번호를 시퀀스를 통해 추출한다
	 * 
	 * @return - 다음 게시글 번호
	 */
	public int selectBoardno();

	/**
	 * 첨부파일 입력
	 * 
	 * @param boardFile - 업로드 된 첨부파일 정보 객체
	 */
	public void insertFile(BoardFile boardFile);

	/**
	 * 첨부파일 조회
	 * 
	 * @param viewBoard - 첨부파일을 조회할 게시글 객체
	 * @return BoardFile - 조회된 첨부파일
	 */
	public BoardFile selectFile(Board viewBoard);

	/**
	 * 
	 * 파일을 삭제한다.
	 * 
	 * @param board - Service 에서 넘어온 삭제객체 정보
	 * 
	 */
	public void daoDelete(Board board);

}
