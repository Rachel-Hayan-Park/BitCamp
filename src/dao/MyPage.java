package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import vo.memberVO.Member;

public class MyPage {
	Scanner sc = new Scanner(System.in);

	final String DRIVER = "oracle.jdbc.OracleDriver"; 
	final String URL = "jdbc:oracle:thin:@192.168.0.121:1521:xe";
	final String USER = "tutoringgo";
	final String PASSWORD = "tutoringgopw";
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MyPage() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 연결 오류");
			e.printStackTrace();
		}
	}
	
	public int updateMember(Member member) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "UPDATE member "
					   + "SET password = ? "
					   + ", name = ? "
					   + ", phone = ? "
					   + ", exp = ? "
					   + "WHERE id = ? ";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getExp());
			pstmt.setString(5, member.getId());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	public int deleteMember(Member member) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "UPDATE member "
					   + "SET joinstatus = ? "
					   + "WHERE id = ? ";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "02");
			pstmt.setString(2, member.getId());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	//튜터 탈퇴 시 승인된 수업이 있을 때
	public int deleteInfoTutor(String loginId) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "" + "SELECT A.TITLE, "
							+ "A.TUTOR_ID, "
							+ "B.ID, "
							+ "A.CLASS_START, "
							+ "A.CLASS_OVER "
							+ "FROM CLASSINFO A, MEMBER B "
							+ "WHERE A.TUTOR_ID = B.ID "
							+ "AND A.TUTOR_ID = ? "
							+ "AND A.ACCEPTSTATUS = '03' "
							+ "AND A.CLASS_START <= SYSDATE "
							+ "AND A.CLASS_OVER >= SYSDATE";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			
			result = pstmt.executeUpdate();		
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	public int deleteInfoStudent(String loginId) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "" + "SELECT * "
							+ "FROM CLASSINFO "
							+ "WHERE student_id = ? "
							+ "AND ACCEPTSTATUS = '03' "
							+ "AND A.CLASS_START <= SYSDATE "
							+ "AND A.CLASS_OVER >= SYSDATE ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			
			result = pstmt.executeUpdate();		
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	private void close(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
