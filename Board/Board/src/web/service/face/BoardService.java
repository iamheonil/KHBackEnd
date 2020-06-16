package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Board;
import web.dto.BoardFile;
import web.util.Paging;

public interface BoardService {

	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<Board> - 게시글 전체 조회 결과 리스트
	 */
	public List<Board> getList();

	/**
	 * 페이징 객체 생성
	 * 
	 * 요청정보를 활용하여 curPage를 구하고
	 * Board 테이블과 curPage 값을 이용한 Paging 객체를 생성하여 반환한다
	 * 
	 * @param req - curPage정보를 담고 있는 요청정보 객체
	 * @return Paging - 페이징 계산이 완료된 결과 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 페이징 처리하여 보여질 게시글 목록만 조회하기
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 조회된 게시글 목록
	 */
	public List<Board> getList(Paging paging);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Board - 전달파라미터 boardno를 포함한 객체
	 */
	public Board getBoardno(HttpServletRequest req);

	/**
	 * 주어진 boardno를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param boardno - boardno를 가지고 있는 객체
	 * @return Board - 조회된 게시글
	 */
	public Board view(Board boardno);
	
	
	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 *  첨부파일을 함께 업로드 할 수 있도록 처리
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	/**
	 * Board 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Board viewBoard);

	/**
	 * 첨부파일의 정보 얻기
	 *  
	 * @param viewBoard - 첨부파일 포함된 게시글 번호
	 * @return BoardFile - 첨부파일 정보 객체
	 */
	public BoardFile viewFile(Board viewBoard);
	
	/**
	 * 
	 * 글을 삭제한다.
	 * 
	 * @param board - 글 삭제할 객체 정보
	 */
	public void delete(Board board);
	
}
