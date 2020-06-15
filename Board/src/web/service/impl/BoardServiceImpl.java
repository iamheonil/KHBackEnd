package web.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	public List<Board> getList() {

		List<Board> boardList = boardDao.selectAll();

		return boardList;
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {

		Board boardno = new Board();
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

		Board board = boardDao.selectBoardByBoardno(boardno);

		if (board != null) {
			// 게시글 조회수 증가
			boardDao.updateHit(boardno);
		}

		return board;
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달파라미터 curPage를 파싱한다.
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntAll();

		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);

		// 계산된 Paging 객체 반환
		return paging;
	}

	@Override
	public Board getBoard(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Board board = new Board();

		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		board.setId(req.getParameter("writer"));

		return board;
	}

	@Override
	public List<Board> getList(Paging paging) {

		return boardDao.selectAll(paging);
	}

	@Override
	public void write(Board b) {

		boardDao.insert(b);

	}

	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {

		// ParamData paramData = new ParamData();

		// Response Object Content-Type Setting
		resp.setContentType("text/html; charset=utf-8");

		// Response Object Write Stream
		PrintWriter out = null;

		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 1. 파일 업로드 형태가 데이터가 맞는지 검사한다.
		// 요청 메시지 Content-Type 이 multipart/form-data 가 맞는지 확인한다.
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우 처리 중단하기
		if (!isMultipart) {
			out.append("<h1>enctype이 multipart/form-data 가 아닙니다.</h1>");
			out.append("<a href='/commons/fileupload'>업로드 페이지로 이동</a>");

			return;
		}

		// 1-2. 여기 이후부터는 multipart/form-data 로 요청된 상황
		// 파일이 전송되었음. 정상적인 흐름으로 처리

		System.out.println("TEST");

		// 2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성
		// ItemFactory : 업로드된 데이터를 처리하는 방식을 설정하는 클래스
		//

		// FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것

		// DiskFileItemFactory class
		// HDD 기반의 파일 아이템 처리 API
		// 업로드된 파일을 HDD 에 임시 저장하고 나중에 처리한다.
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 3. 업로드 된 파일아이템의 용량이 적당히 작으면 메모리에서 처리하도록 설정

		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);

		// 4. 용량이 적당히 크면 임시파일(HDD)을 만들어서 처리하도록 설정, 임시파일 폴더 설정
		ServletContext context = req.getServletContext();

		String path = context.getRealPath("tmp");

		File repository = new File(path); // 임시 저장 폴더

		System.out.println("repository : " + repository);

		factory.setRepository(repository);

		// 5. 업로드 허용 용량 기준을 넘지 않을 경우에만 파일 업로드 되도록 설정,
		// 기준을 넘으면 업로드 차단한다.
		int maxFile = 10 * 1024 * 1024; // 10mb

		// 파일업로드 수행 객체 생성
		// DiskFileItemFactory 객체 이용해서 생성함
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 파일 업로드 용량제한 설정 : 10Mb
		upload.setFileSizeMax(maxFile);

		// ------------------------ 파일 업로드 준비 완료! ------------------------

		// 6. 업로드된 데이터를 추출한다.(Parsing)
		// 임시파일 업로드도 같이 수행된다.
		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 7. 파싱된 요청정보 데이터 처리하기.
		// items 리스트에 요청파라미터 (파일 포함)이 파싱되어 있음
		// 요청 정보의 3가지 형태
		// 1. 빈 파일(용량이 0인 파일)
		// 2. form - data (전달 파라미터)
		// 3. 파일

		// 파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();

		// 모든 요청정보를 처리하는 반복문
		while (iter.hasNext()) {

			// 요청 정보 객체
			FileItem item = iter.next();

			// 1) 빈 파일에 대한 처리
			if (item.getSize() <= 0)
				continue;

			// 2) 기본 전달 파라미터에 대한 처리
			if (item.isFormField()) {
				// form-data (form field) 는 key=value 쌍으로 전달된다.

				// key 는 item.getFieldName() 으로 얻어옴
				// value 는 item.getString() 으로 얻어옴

				// 전달값이 한글일경우 인코딩 설정을 따로 해야한다.
				// item.getString("UTF-8); 을 사용한다.
				// 기존에 사용중이던 req.setCharacterEncoding("UTF-8") 은 적용되지 않는다.

				// 기본 처리 방식
				out.println("= = = = = = = = = 폼 필드 = = = = = = = = = =<br>");
				out.println("키 : " + item.getFieldName() + "<br>");
				try {
					out.println("값 : " + item.getString("UTF-8") + "<br>");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				// 키 값에 따라 처리하는 방식
//				String key = item.getFieldName();
//
//				if ("title".equals(key)) { // 전달 파라미터 name이 "title"
//					try {
//						boardFile.setTitle(item.getString("UTF-8"));
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//				} else if ("data1".equals(key)) { // 전달 파라미터 name이 "data1"
//					try {
//						paramData.setData1(item.getString("UTF-8"));
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//				} else if ("data2".equals(key)) { // 전달 파라미터 name이 "data2"
//					try {
//						paramData.setData2(item.getString("UTF-8"));
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//				}

				// ===========================

			} // if (item.isFormField()) End

			// 3) 파일에 대한 처리
			if (!item.isFormField()) {

				// 서버에 저장되는 파일명을 "년월일시분초밀리초.확장자로 변경
				// 파일명 - 년월일시분초밀리초
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssS");
				String rename = sdf.format(new Date());

				// 확장자
				String origin = item.getName();
				String ext = origin.substring(origin.lastIndexOf(".") + 1);

				// 저장 될 이름
				String stored = rename + "." + ext;

				// System.out.println("TEST !.! origin file name : " + origin);

				// System.out.println("TEST !.! stored file name : " + stored);

				// DB에 업로드된 파일에 대한 정보 기록하기
				BoardFile boardFile = new BoardFile();

				boardFile.setOriginName(origin);
				boardFile.setStoredName(stored);

				System.out.println(boardFile);

				boardDao.insertFile(boardFile);

				// 파일을 처리하는 방식
				// 3.1 ) 파일을 웹 서버의 로컬 디스크에 저장, 파일의 정보를 DB에 기록해야한다.
				// 실제 업로드 파일
				File up = new File(context.getRealPath("upload"), // 업로드 될 폴더
						// item.getName(), // 원본파일의 이름
						stored); // 저장 파일의 이름(변환한 것)

				try {
					item.write(up); // 실제 업로드(최종 결과 파일 생성)
					item.delete(); // 임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 3.2 ) 테이블의 컬럼으로 파일의 내용을 저장, BLOB 타입을 사용한다.
				// 따로 수업에서 다루지는 않는다.
			}

		} // while End

//		Board board = new Board();
//
//		board.setTitle(req.getParameter("title"));
//		board.setTitle(req.getParameter("title"));
//		board.setId(req.getParameter("writer"));

	}

}
