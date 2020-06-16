package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dbutil.JDBCTemplate;
import web.dao.face.BoardFileDao;
import web.dto.Board;
import web.dto.BoardFile;

public class BoardFileDaoImpl implements BoardFileDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public BoardFile getBoardno(HttpServletRequest req) {

		BoardFile boardFileNo = new BoardFile();
		String param = req.getParameter("boardno");
		if (param != null && !"".equals(param)) {

			// boardno 전달파라미터 추출
			boardFileNo.setBoardno(Integer.parseInt(param));
		}
		
		return boardFileNo;
	}
	
	@Override
	public void delete(BoardFile board) {

		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "DELETE FROM BOARDFILE WHERE BOARDNO = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

	}

}
