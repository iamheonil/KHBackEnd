package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.util.Paging;

public class BoardServiceImpl implements BoardService {

	// BoardDao 객체 생성
	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	public List<Board> getList() {

		// 게시글 전체 조회 결과 처리
		return boardDao.selectAll();
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntAll();

		// Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);

		// Paging 객체 반환
		return paging;
	}

	@Override
	public List<Board> getList(Paging paging) {
		return boardDao.selectAll(paging);
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {

		// boardno를 저장할 객체 생성
		Board boardno = new Board();

		// boardno 전달파라미터 검증 - null, ""
		String param = req.getParameter("boardno");
		if (param != null && !"".equals(param)) {

			// boardno 전달파라미터 추출
			boardno.setBoardno(Integer.parseInt(param));
		}

		// 결과 객체 반환
		return boardno;
	}

	@Override
	public Board view(Board boardno) {

		if (boardno != null) {
			// 게시글 조회수 증가
			boardDao.updateHit(boardno);
		}

		// 게시글 조회
		Board board = boardDao.selectBoardByBoardno(boardno);

		return board;
	}

	@Override
	public void write(HttpServletRequest req) {

		// ----------- urlencoded 처리 -----------
//		Board board = new Board();
//
//		board.setTitle( req.getParameter("title") );
//		board.setContent( req.getParameter("content") );
//
//		//작성자id 처리
//		board.setId((String) req.getSession().getAttribute("userid"));
//
//		if(board.getTitle()==null || "".equals(board.getTitle())) {
//			board.setTitle("(제목없음)");
//		}
//
//		boardDao.insert(board);
		// ---------------------------------------

		// 게시글 정보 저장할 객체
		Board board = null;

		// 첨부파일 정보 저장할 객체
		BoardFile boardFile = null;

		// 파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");

			return; // fileupload() 메소드 실행 중지
		}

		// 게시글 정보 저장할 객체 생성
		board = new Board();

		// 디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024); // 1MB

		// 임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		factory.setRepository(repository);

		// 파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024); // 10MB

		// 전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		// 모든 요청 정보 처리하기
		while (iter.hasNext()) {
			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			// 2) 일반적인 요청 데이터 처리
			if (item.isFormField()) {

				String key = item.getFieldName(); // 키 추출
				if ("title".equals(key)) { // 전달파라미터 name이 "title"
					try {
						board.setTitle(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} else if ("content".equals(key)) { // 전달파라미터 name이 "content"
					try {
						board.setContent(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} // key값 비교 if end

			} // if( item.isFormField() ) end - 폼필드 확인

			// 3) 파일 처리
			if (!item.isFormField()) {

				// --- UUID 생성 ---
				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성
				String u = uuid.toString().split("-")[0]; // 8자리 uid
				// -----------------

				// --- 로컬 저장소의 파일 객체 생성 ---
				File up = new File(req.getServletContext().getRealPath("upload") // 업로드될 폴더 경로
						, item.getName() + "_" + u // 원본파일명_uid
				);
				// ------------------------------------

				// --- 첨부파일 정보 객체 ---
				boardFile = new BoardFile(); // 객체 생성
				boardFile.setOriginname(item.getName()); // 원본파일명
				boardFile.setStoredname(item.getName() + "_" + u); // 저장파일명
				boardFile.setFilesize((int) item.getSize());
				// --------------------------

				// --- 처리 완료된 파일 업로드 하기 ---
				try {
					item.write(up); // 실제 업로드
					item.delete(); // 임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				// -----------------------------------

			} // 파일 처리 end

		} // while( iter.hasNext() ) end - FileItem 반복 처리

		// DB데이터 입력

		// 게시글 작성자 id 입력
		board.setId((String) req.getSession().getAttribute("userid"));

		// 게시글 번호 생성 - Dao 이용
		int boardno = boardDao.selectBoardno();

		// 게시글 정보가 있을 경우
		if (board != null) {

			// 게시글 번호 입력
			board.setBoardno(boardno);

			// 게시글 삽입
			boardDao.insert(board);
		}

		// 첨부파일 정보가 있을 경우
		if (boardFile != null) {
			// 게시글 번호 입력
			boardFile.setBoardno(boardno);

			// 첨부파일 삽입
			boardDao.insertFile(boardFile);
		}

	}

	@Override
	public String getNick(Board viewBoard) {
		return boardDao.selectNickByUserid(viewBoard);
	}

	@Override
	public BoardFile viewFile(Board viewBoard) {
		return boardDao.selectFile(viewBoard);
	}

	@Override
	public void delete(Board board) {

		boardDao.fileDelete(board);
		boardDao.daoDelete(board);

	}

	@Override
	public void update(Board board, HttpServletRequest req) {

		boardDao.daoUpdate(board, req);
	}

}
