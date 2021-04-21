package dao.studentDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.classVO.ClassInfo;

public class Student_ClassManager {
	final String DRIVER = "oracle.jdbc.OracleDriver"; 
	final String URL = "jdbc:oracle:thin:@192.168.0.121:1521:xe";
	final String USER = "tutoringgo";
	final String PASSWORD = "tutoringgopw";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Student_ClassManager() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 연결 오류");
			e.printStackTrace();
		}
	}
	
	public List<ClassInfo> applyResult(String student_id) {	// 학생이 신청한 수업 내역 결과 
		List<ClassInfo> list = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
						
			String sql = "SELECT c.classNum "
						+ ", TO_CHAR(c.class_start, 'YYYY/MM/DD') class_start "
						+ ", TO_CHAR(c.class_over, 'YYYY/MM/DD') class_over "
						+ ", c.student_id "
						+ ", c.acceptstatus "
						+ ", r.rejectstatus "
						+ ", c.title "
						+ ", cs.cd_name as AC "
						+ "FROM classinfo c LEFT JOIN reject r ON c.classnum = r.classnum "
						+ "INNER JOIN comm_code cs ON (cs.cd_code = r.rejectstatus OR cs.cd_code = c.acceptstatus) "
						+ "AND cs.cd_type = 'CS' " 
						+ "WHERE (c.student_id = ? OR r.student_id = ? ) " 
						+ "AND (c.acceptstatus = '01' or c.acceptstatus = '03' or r.rejectstatus = '02') " 
						+ "AND c.class_start >= sysdate ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student_id);
			pstmt.setString(2, student_id);
			
			rs = pstmt.executeQuery();
						
			while (rs.next()) {
				ClassInfo ci = new ClassInfo();
				ci.applyResult( rs.getString("AC")
							  , rs.getString("classnum")
							  , rs.getString("class_start")
							  , rs.getString("class_over")
							  , rs.getString("title"));
				list.add(ci);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<ClassInfo> classList(int number, String loginId) {
		List<ClassInfo> list = null;
		ClassInfo ci = new ClassInfo();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "";
			if (number == 0) {	// 현재
				sql = "" + "SELECT "
						+ "CLASSNUM, "
						+ "TITLE, "
						+ "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS class_start, " 
						+ "TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over, " 
						+ "tutor_id "
						+ "FROM CLASSINFO " 
						+ "WHERE CLASS_START <= SYSDATE "
						+ "AND CLASS_OVER >= SYSDATE "
						+ "AND STUDENT_ID IS NOT NULL "
						+ "AND acceptstatus = '03' "// 승인된 수업만
						+ "AND TUTOR_ID = ? ";
			}
			else if (number == 1) {		// 과거
				sql = "" + "SELECT "
						 + "CLASSNUM, "
						 + "TITLE, "
				  		 + "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS class_start, " 
						 + "TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over, " 
						 + "tutor_id "
						 + "FROM CLASSINFO " 
						 + "WHERE CLASS_START < SYSDATE "
						 + "AND CLASS_OVER < SYSDATE "
						 + "AND STUDENT_ID IS NOT NULL "
						 + "AND acceptstatus = '03' "// 승인된 수업만
						 + "AND TUTOR_ID = ? ";
			}
			else if (number == 2) {		// 예정
				sql = "" + "SELECT "
					    + "CLASSNUM, "
					    + "TITLE, "
					    + "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS class_start, " 
					    + "TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over, " 
					    + "tutor_id "
					    + "FROM CLASSINFO " 
					    + "WHERE CLASS_START > SYSDATE "
					    + "AND STUDENT_ID IS NOT NULL "
					    + "AND acceptstatus = '03' "// 승인된 수업만
					    + "AND TUTOR_ID = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);	
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while (rs.next()) {
				ci.classList( rs.getString("CLASSNUM")
						    , rs.getString("TITLE")
						    , rs.getString("CLASS_START")
						    , rs.getString("CLASS_OVER")
						    , rs.getString("tutor_id"));

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
