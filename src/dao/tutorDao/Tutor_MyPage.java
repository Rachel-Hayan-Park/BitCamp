package dao.tutorDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.classVO.ClassInfo;

public class Tutor_MyPage {
	final String DRIVER = "oracle.jdbc.OracleDriver"; 
	final String URL = "jdbc:oracle:thin:@192.168.0.121:1521:xe";
	final String USER = "tutoringgo";
	final String PASSWORD = "tutoringgopw";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Tutor_MyPage() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 연결 오류");
			e.printStackTrace();
		}
	}	
	
	public List<ClassInfo> classList(int number, String loginId) {
		List<ClassInfo> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "";
			if (number == 0) {	// 현재
				sql = "" + "SELECT "
						+ "CLASSNUM, "
						+ "TITLE, "
						+ "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS class_start, " 
						+ "TO_CHAR(class_over, 'YYYY/MM/DD') AS clasS_over, " 
						+ "STUDENT_ID "
						+ "FROM CLASSINFO " 
						+ "WHERE CLASS_START <= SYSDATE "
						+ "AND CLASS_OVER >= SYSDATE "
						+ "AND STUDENT_ID IS NOT NULL "
						+ "AND TUTOR_ID = ? ";
			}
			else if (number == 1) {		// 과거
				sql = "" + "SELECT "
						 + "CLASSNUM, "
						 + "TITLE, "
				  		 + "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS class_start, " 
						 + "TO_CHAR(class_over, 'YYYY/MM/DD') AS clasS_over, " 
						 + "STUDENT_ID "
						 + "FROM CLASSINFO " 
						 + "WHERE CLASS_START < SYSDATE "
						 + "AND CLASS_OVER < SYSDATE "
						 + "AND STUDENT_ID IS NOT NULL "
						 + "AND TUTOR_ID = ? ";
			}
			else if (number == 2) {		// 예정
				sql = "" + "SELECT "
					    + "CLASSNUM, "
					    + "TITLE, "
					    + "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS class_start, " 
					    + "TO_CHAR(class_over, 'YYYY/MM/DD') AS clasS_over, " 
					    + "STUDENT_ID "
					    + "FROM CLASSINFO " 
					    + "WHERE CLASS_START > SYSDATE "
					    + "AND STUDENT_ID IS NOT NULL "
					    + "AND TUTOR_ID = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);	
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while (rs.next()) {
				ClassInfo ci = new ClassInfo(
					rs.getString("CLASSNUM"),
					rs.getString("TITLE"),
					rs.getString("CLASS_START"),
					rs.getString("CLASS_OVER"),
					rs.getString("STUDENT_ID"));
					list.add(ci);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
