package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BoardDao;
import web.dbutil.JDBCTemplate;
import web.dto.Board;
import web.dto.BoardFile;
import web.util.Paging;

public class BoardDaoImpl implements BoardDao {

	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	@Override
	public List<Board> selectAll() {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " ORDER BY boardno DESC";

		// 결과 저장할 List
		List<Board> boardList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				Board b = new Board(); // 결과값 저장 객체

				// 결과값 한 행 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setTitle(rs.getString("title"));
				b.setId(rs.getString("id"));
				b.setContent(rs.getString("content"));
				b.setHit(rs.getInt("hit"));
				b.setWrittendate(rs.getDate("writtendate"));

				// 리스트에 결과값 저장
				boardList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return boardList;
	}

	@Override
	public int selectCntAll() {

		conn = JDBCTemplate.getConnection(); // DB연결

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board";

		// 최종 결과값
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 반환

			// 조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

	@Override
	public List<Board> selectAll(Paging paging) {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, B.* FROM (";
		sql += "        SELECT";
		sql += "            boardno, title, id,";
		sql += "            content, hit, writtendate";
		sql += "        FROM board";
		sql += "        ORDER BY boardno DESC";
		sql += "    ) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<Board> boardList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo()); // 페이징 게시글 시작 번호
			ps.setInt(2, paging.getEndNo()); // 페이징 게시글 끝 번호

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				Board b = new Board(); // 결과값 저장 객체

				// 결과값 한 행 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setTitle(rs.getString("title"));
				b.setId(rs.getString("id"));
				b.setContent(rs.getString("content"));
				b.setHit(rs.getInt("hit"));
				b.setWrittendate(rs.getDate("writtendate"));

				// 리스트에 결과값 저장
				boardList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return boardList;
	}

	@Override
	public Board selectBoardByBoardno(Board boardno) {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE boardno = ?";

		// 결과 저장할 Board객체
		Board viewBoard = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno()); // 조회할 게시글 번호 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				viewBoard = new Board(); // 결과값 저장 객체

				// 결과값 한 행 처리
				viewBoard.setBoardno(rs.getInt("boardno"));
				viewBoard.setTitle(rs.getString("title"));
				viewBoard.setId(rs.getString("id"));
				viewBoard.setContent(rs.getString("content"));
				viewBoard.setHit(rs.getInt("hit"));
				viewBoard.setWrittendate(rs.getDate("writtendate"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return viewBoard;
	}

	@Override
	public void updateHit(Board boardno) {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "UPDATE board";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno()); // 조회할 게시글 번호 적용

			ps.executeUpdate(); // SQL 수행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public void insert(Board board) {

		conn = JDBCTemplate.getConnection(); // DB 연결

		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO board(BOARDNO,TITLE,ID,CONTENT,HIT) ";
		sql += " VALUES (?, ?, ?, ?, 0)";

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getId());
			ps.setString(4, board.getContent());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public String selectNickByUserid(Board viewBoard) {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT usernick FROM member";
		sql += " WHERE userid = ?";

		// 결과 저장할 String 변수
		String usernick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setString(1, viewBoard.getId()); // 조회할 id 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				usernick = rs.getString("usernick");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return usernick;

	}

	@Override
	public int selectBoardno() {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT board_seq.nextval FROM dual";

		// 결과 저장할 변수
		int boardno = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				boardno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return boardno;
	}

	@Override
	public void insertFile(BoardFile boardFile) {

		conn = JDBCTemplate.getConnection(); // DB 연결

		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO boardfile(fileno, boardno, originname, storedname, filesize)";
		sql += " VALUES ( boardfile_seq.nextval, ?, ?, ?, ? )";

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public BoardFile selectFile(Board viewBoard) {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE boardno = ?";

		// 결과 저장할 BoardFile 객체
		BoardFile boardFile = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewBoard.getBoardno()); // 조회할 boardno 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				boardFile = new BoardFile();

				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setOriginname(rs.getString("originname"));
				boardFile.setStoredname(rs.getString("storedname"));
				boardFile.setFilesize(rs.getInt("filesize"));
				boardFile.setWritedate(rs.getDate("writtendate"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return boardFile;

	}

	@Override
	public void daoDelete(Board board) {
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "DELETE FROM BOARD WHERE BOARDNO = ?";

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, board.getBoardno()); // 게시글 번호 적용

			ps.executeUpdate(); // SQL 수행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public void fileDelete(Board board) {
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "DELETE FROM BOARDFILE WHERE BOARDNO = ?";

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, board.getBoardno()); // 게시글 번호 적용

			ps.executeUpdate(); // SQL 수행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public Board selectByBoardno(Board board) {
		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE boardno = ?";

		// 결과 저장할 Board객체
		Board viewBoard = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, board.getBoardno()); // 조회할 게시글 번호 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				viewBoard = new Board(); // 결과값 저장 객체

				// 결과값 한 행 처리
				viewBoard.setBoardno(rs.getInt("boardno"));
				viewBoard.setTitle(rs.getString("title"));
				viewBoard.setId(rs.getString("id"));
				viewBoard.setContent(rs.getString("content"));
				viewBoard.setHit(rs.getInt("hit"));
				viewBoard.setWrittendate(rs.getDate("writtendate"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return viewBoard;
	}

	@Override
	public void daoUpdate(Board board, HttpServletRequest req) {

		conn = JDBCTemplate.getConnection(); // DB 연결

		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE BOARD SET TITLE = ?, CONTENT = ?";
		sql += " WHERE board = ? ";

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoardno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

}
