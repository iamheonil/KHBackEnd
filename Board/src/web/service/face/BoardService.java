package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.util.Paging;

public interface BoardService {

	/**
	 * 리스트(글 목록)를 가져온다.
	 * 
	 * @return list;
	 */
	public List<Board> getList();

	/**
	 * 
	 * @param req - no 를 받아 해당 글을 출력한다.
	 * @return view - 해당 글 번호의 내용을 view에 담아 내보낸다.
	 */
	public Board getBoardno(HttpServletRequest req);

	
	/**
	 * 조회수를 +1
	 * @param b
	 * @return - board 
	 */
	public Board view(Board b);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 페이징 처리하여 보여질 리스트(글 목록)를 가져온다.
	 * 
	 * @return list; - 게시글 전체 조회 결과 리스트
	 */
	public List<Board> getList(Paging paging);
	
	
	/**
	 * 게시글을 삽입한다.
	 * @param b - 요청 정보 객체
	 * @return board - 삽입이 완료된 객체
	 */
	public void write(Board b);
	
	
	/**
	 * 게시글을 삽입한다.
	 * (첨부파일 기능 추가)
	 * @param req - 요청 정보 객체
	 */
	public void write(HttpServletRequest req, HttpServletResponse resp);
	
	
	/**
	 * 게시글을 작성, 제목과 Content 부분을 받아옴
	 * @param req - 게시글의 제목과 내용 부분
	 * @return Board - 게시글 
	 */
	public Board getBoard(HttpServletRequest req);
	

}
