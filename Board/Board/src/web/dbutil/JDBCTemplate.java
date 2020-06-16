package web.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 싱글톤 객체 - DB연결
public class JDBCTemplate {
	
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//OJDBC 객체
	private static Connection conn = null; //DB연결객체

	//private 생성자
	private JDBCTemplate() { }
	
	// Connection 객체 반환 - 싱글톤 패턴 적용
	public static Connection getConnection() {
	
		if( conn == null ) { // 한번 생성된 객체를 유지하게 만듦
			
			try {
				//----- 드라이버 로드 -----
				Class.forName(DRIVER);

				//----- DB 연결 -----
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return conn; //DB연결객체 반환
	}
	
	
	//커밋 수행 메소드
	public static void commit(Connection conn) {
		try {
			if( conn != null && !conn.isClosed() ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//롤백 수행 메소드
	public static void rollback(Connection conn) {
		try {
			if( conn != null && !conn.isClosed() ) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps) {
		try {
			if(ps!=null && !ps.isClosed()) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}





