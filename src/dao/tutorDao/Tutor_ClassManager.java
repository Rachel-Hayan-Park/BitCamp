package dao.tutorDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.classVO.ClassInfo;

public class Tutor_ClassManager {
	final String DRIVER = "oracle.jdbc.OracleDriver"; 
	final String URL = "jdbc:oracle:thin:@192.168.0.121:1521:xe";
	final String USER = "tutoringgo";
	final String PASSWORD = "tutoringgopw";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Tutor_ClassManager() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 연결 오류");
			e.printStackTrace();
		}
	}
	
	// 학생으로부터 온 강의 요청 내역 꺼내어 List에 담기 
	public List<ClassInfo> selectStatus(String loginId) {
		List<ClassInfo> list = null;
	
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "" + "SELECT A.CLASSNUM"
								 + ", A.TITLE"
								 + ", TO_CHAR(class_start, 'YYYY/MM/DD') AS CLASS_START"
								 + ", TO_CHAR(class_over, 'YYYY/MM/DD') AS CLASS_OVER"
								 + ", A.STUDENT_ID"
								 + ", A.ACCEPTSTATUS"
								 + ", B.CD_NAME "
							  + "FROM CLASSINFO A, COMM_CODE B " 
							+ " WHERE A.ACCEPTSTATUS = B.CD_CODE "
							+ "   AND B.CD_TYPE		 = 'CS'" 
							+ "   AND A.ACCEPTSTATUS = '01'" 
							+ "   AND A.TUTOR_ID     = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, loginId);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ClassInfo>();
			while(rs.next()) {
				ClassInfo ci = new ClassInfo(
						rs.getString("CLASSNUM"),
						rs.getString("TITLE"),
						rs.getString("CLASS_START"),
						rs.getString("CLASS_OVER"),
						rs.getString("STUDENT_ID"),
						rs.getString("ACCEPTSTATUS"),
						rs.getString("CD_NAME"));		
	
						list.add(ci);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 학생의 승인요청 승인
	public int updateStatus(String classNum, String acceptstatus) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "" + "UPDATE CLASSINFO "
				    + "   SET ACCEPTSTATUS = ? "
				    + " WHERE ACCEPTSTATUS = '01' "
			        +   " AND CLASSNUM = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, acceptstatus);
			pstmt.setString(2, classNum); 
			
			result = pstmt.executeUpdate();	
			
			// 거절된 수업에 한하여 클래스인포 테이블에서 student_id, acceptstatus -> null로 다시 만들어주기.
			if ("02".equals(acceptstatus))
			{
				sql = "UPDATE CLASSINFO "
				    + "   SET ACCEPTSTATUS = null "
					+ "     , student_id = null "
				    + " WHERE ACCEPTSTATUS = '02' "
			        +   " AND CLASSNUM = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, classNum); 
				
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	public int insertStatus(ClassInfo ci, String loginId) {
		int result = 0;
		
		try 
		{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
	
			String sql = "" + "INSERT INTO CLASSINFO " 
							+ "	  (CLASSNUM, REGION, CATEGORY, TITLE, "
							+ "CLASS_START, CLASS_OVER, PRICE, INTRO, TUTOR_ID, ACCEPTSTATUS) "
							+ "VALUES (SEQ_CLASSNUM.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ci.getRegion());
			pstmt.setString(2, ci.getCategory());
			pstmt.setString(3, ci.getTitle());
			pstmt.setString(4, ci.getClass_start());
			pstmt.setString(5, ci.getClass_over());
			pstmt.setString(6, ci.getPrice());
			pstmt.setString(7, ci.getIntro());
			pstmt.setString(8, loginId);
			pstmt.setString(9, ci.getAcceptStatus());
			
			result = pstmt.executeUpdate();
			
		}
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println("정보가 잘못 입력되었습니다.");
		}
		finally 
		{
			close(conn, pstmt);
		}
		return result;
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
						+ "and acceptstatus = '03' "
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
						 + "and acceptstatus = '03' "
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
					    + "and acceptstatus = '03' "
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

	public List<ClassInfo> selectClass(String loginId) {
		List<ClassInfo> list = null;
		try 
		{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "" + "SELECT "
					 		+ "A.CLASSNUM, "
					 		+ "A.REGION, "
					 		+ "(SELECT B.CD_NAME FROM COMM_CODE B WHERE B.CD_CODE = A.REGION AND B.CD_TYPE = 'AR') AS AREA, "
							+ "A.CATEGORY, "
					 		+ "(SELECT C.CD_NAME FROM COMM_CODE C WHERE C.CD_CODE = A.CATEGORY AND C.CD_TYPE = 'CT') AS SORT,  "
					 		+ "A.TITLE, " 
					 		+ "A.INTRO, "
					 		+ "TO_CHAR(CLASS_START, 'YYYY/MM/DD') AS CLASS_START, "
					 		+ "TO_CHAR(class_over, 'YYYY/MM/DD') AS CLASS_OVER, "
					 		+ "TO_CHAR(price, '999,999') || '원' AS PRICE, "
					 		+ "A.STUDENT_ID, "
					 		+ "A.ACCEPTSTATUS, "
					 		+ "(SELECT D.CD_NAME FROM COMM_CODE D WHERE D.CD_CODE = A.ACCEPTSTATUS AND D.CD_TYPE = 'CS') AS STATUS "
					 		+ "FROM CLASSINFO A "
					 		+ "WHERE tutor_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);	
			
			rs = pstmt.executeQuery();
		
			list = new ArrayList<ClassInfo>();
			while(rs.next()) {
				ClassInfo ci = new ClassInfo();
				ci.selectClass( rs.getString("CLASSNUM")
							  , rs.getString("REGION")
							  , rs.getString("AREA")
							  , rs.getString("CATEGORY")
							  , rs.getString("SORT")
							  , rs.getString("TITLE")
							  , rs.getString("INTRO")
							  , rs.getString("CLASS_START")
							  , rs.getString("CLASS_OVER")
							  , rs.getString("PRICE")
							  , rs.getString("STUDENT_ID")
							  , rs.getString("ACCEPTSTATUS")
							  , rs.getString("STATUS"));
				list.add(ci);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int updateClass(ClassInfo ci, String loginId) {
		int result = 0;
		try 
		{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "" + "UPDATE CLASSINFO "
							+ "SET REGION = ?, "
							+ "CATEGORY = ?, "
							+ "TITLE = ?, "
							+ "CLASS_START = ?, "
							+ "CLASS_OVER = ?, "
							+ "PRICE = ?, "
							+ "INTRO = ? "
							+ "WHERE TUTOR_ID = ? "
							+ "AND CLASSNUM = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ci.getRegion());
			pstmt.setString(2, ci.getCategory());
			pstmt.setString(3, ci.getTitle());
			pstmt.setString(4, ci.getClass_start());
			pstmt.setString(5, ci.getClass_over());
			pstmt.setString(6, ci.getPrice());
			pstmt.setString(7, ci.getIntro());
			pstmt.setString(8, loginId);
			pstmt.setString(9, ci.getClassNum());
			
			result = pstmt.executeUpdate();	
			
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println("정보가 잘못 입력되었습니다.");
		} 
		finally 
		{
			close(conn, pstmt);
		}
		return result;
	}
	
	//수업 삭제
	public int deleteclass(ClassInfo ci, String loginId) {
		int result = 0;
		
		try 
		{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "" + "DELETE FROM CLASSINFO "
							+ "WHERE CLASSNUM = ? "
							+ "AND TUTOR_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ci.getClassNum());
			pstmt.setString(2, loginId);
			
			result = pstmt.executeUpdate();
		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
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

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ClassInfo> classCheck(String classNum, int number) {
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
						+ "and acceptstatus = '03' "
						+ "AND classnum = ? ";
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
					    + "and acceptstatus = '03' "
					    + "AND STUDENT_ID IS NOT NULL "
					    + "AND classnum = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classNum);	
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

}
