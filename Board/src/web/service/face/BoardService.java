package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

}
