package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.JDBCTemplate;
import web.dao.face.MemberDao;
import web.dto.Member;

public class MemberDaoImpl implements MemberDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Member selectMemberByUserid(Member member) {
		conn = JDBCTemplate.getConnection();

		String sql = "";

		sql += "SELECT * FROM MEMBER WHERE USERID = ? AND USERPW = ?;";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getId());
			ps.setString(2, member.getPw());

			rs = ps.executeQuery();

			while (rs.next()) {

				member = new Member();

				member.setId(rs.getString("userid"));
				member.setPw(rs.getString("userpw"));
				member.setNick(rs.getString("usernick"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return member;
	}

	@Override
	public int selectCntMemberByUseridUserpw(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

}
