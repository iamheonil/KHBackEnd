package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.JDBCTemplate;
import web.dao.face.BoardDao;
import web.dto.Board;
import web.util.Paging;

public class BoardDaoImpl implements BoardDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Board> selectAll() {

		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM BOARD ORDER BY BOARDNO DESC";

		List<Board> blist = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));

				blist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return blist;
	}

	@Override
	public Board selectBoardByBoardno(int no) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM BOARD WHERE BOARDNO = ? ORDER BY BOARDNO ";

		Board blist = new Board();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, no);

			rs = ps.executeQuery();

			while (rs.next()) {

				blist.setBoardno(rs.getInt("boardno"));
				blist.setTitle(rs.getString("title"));
				blist.setId(rs.getString("id"));
				blist.setContent(rs.getString("content"));
				blist.setHit(rs.getInt("hit"));
				blist.setWrittendate(rs.getDate("writtendate"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return blist;

	}

	@Override
	public void updateHit(Board b) {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT COUNT (*) FROM BOARD";

		// 결과 저장할 변수
		int totalCount = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				totalCount = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return totalCount;
	}
	/*
	 * @Override public void updateHit(Board b) { conn =
	 * JDBCTemplate.getConnection();
	 * 
	 * String sql = ""; // sql +=
	 * "SELECT * FROM BOARD WHERE BOARDNO = ? ORDER BY BOARDNO ";
	 * 
	 * sql += "SELECT * FROM BOARD";
	 * 
	 * Board blist = new Board();
	 * 
	 * try { ps = conn.prepareStatement(sql);
	 * 
	 * 
	 * while (rs.next()) {
	 * 
	 * blist.setBoardno(rs.getInt("boardno"));
	 * blist.setTitle(rs.getString("title")); blist.setId(rs.getString("id"));
	 * blist.setContent(rs.getString("content")); blist.setHit(rs.getInt("hit"));
	 * blist.setWrittendate(rs.getDate("writtendate"));
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } finally {
	 * JDBCTemplate.close(rs); JDBCTemplate.close(ps); } }
	 */

	@Override
	public List<Board> selectAll(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM ( SELECT ROWNUM RNUM, B.* FROM ( SELECT BOARDNO, TITLE, ID, CONTENT, HIT, writtendate FROM BOARD ORDER BY BOARDNO DESC ) B ) BOARD WHERE RNUM BETWEEN ? AND ?";

		List<Board> blist = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));

				blist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return blist;
	}
}
