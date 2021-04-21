package dao.studentDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import vo.classVO.ClassInfo;
import vo.memberVO.Member;

public class Student_Select {
	Scanner sc = new Scanner(System.in);

	final String DRIVER = "oracle.jdbc.OracleDriver"; 
	final String URL = "jdbc:oracle:thin:@192.168.0.121:1521:xe";
	final String USER = "tutoringgo";
	final String PASSWORD = "tutoringgopw";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Student_Select() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 연결 오류");
			e.printStackTrace();
		}
	}
	
	public void selectAll() {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
						
			String sql = "SELECT classnum"
							+ ", AR.cd_name as region"
							+ ", CT.cd_name as category"
							+ ", TO_CHAR(class_start, 'YYYY/MM/DD') AS class_start"
							+ ", TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over"
							+ ", title "
					   + "FROM classinfo "
					   + "INNER JOIN comm_code ar ON region = ar.cd_code AND ar.cd_type = 'AR' "
					   + "INNER JOIN comm_code ct ON category = ct.cd_code AND ct.cd_type = 'CT' "
					   + "INNER JOIN member m ON tutor_id = m.id "
					   + "WHERE student_id IS NULL "
					   + "AND class_over > sysdate "
					   + "AND m.joinstatus = '01' "	// 가입한 튜터의 수업 정보만 조회 가능
					   + "ORDER BY classnum ASC ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
						
			while (rs.next()) {
				String str = "";
				str += rs.getString("classnum") + "\t";
				str += rs.getString("region") + "\t";
				str += rs.getString("category") + "\t";
				str += rs.getString("class_start") + "\t";
				str += rs.getString("class_over") + "\t";
				str += rs.getString("title") + "\t ";
				System.out.println(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	
	}
	
	public ClassInfo selectOneClass(String classNum) {
		ClassInfo ci = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "SELECT classnum "
							+ ", r.cd_name as region "
							+ ", c.cd_name as category "
							+ ", title "
							+ ", intro "
							+ ", TO_CHAR(class_start, 'YYYY/MM/DD') AS class_start "
							+ ", TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over "
							+ ", TO_CHAR(price, '999,999') || '원' as price "
							+ ", tutor_id "
							+ ", m.exp as exp "
					   + "FROM classinfo "
					   + "INNER JOIN comm_code r ON region = r.cd_code AND r.cd_type = 'AR' "
					   + "INNER JOIN comm_code c ON category = c.cd_code AND c.cd_type = 'CT' "
					   + "LEFT JOIN member m ON tutor_id = m.id "
					   + "WHERE classnum = ? AND student_id IS NULL "
					   + "AND m.joinstatus = '01' ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classNum);
						
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String region = rs.getString("region");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String intro = rs.getString("intro");
				String class_start = rs.getString("class_start");
				String class_over = rs.getString("class_over");
				String price = rs.getString("price");
				String tutor_id = rs.getString("tutor_id");
				String exp = rs.getString("exp");
				
				ci = new ClassInfo(classNum, region, category, title, 
								   class_start, class_over, price, intro, tutor_id, exp);
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return ci;
	}
	
	public int applyClass(List<Member> list, String classNum) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE classinfo ");
			sb.append("SET student_id = ? ");
			sb.append("	 , acceptstatus = ? ");
			sb.append("WHERE classNum = ? ");
			String sql = sb.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, list.get(0).getId());
			pstmt.setString(2, "01");
			pstmt.setString(3, classNum);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
		
	public void searchFilter(String category, String region, String class_start, String class_over) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
					
			String sql = "SELECT classnum "
							+ ", ct.cd_name as category "
							+ ", ar.cd_name as region "
							+ ", TO_CHAR(class_start, 'YYYY/MM/DD') AS class_start "
							+ ", TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over "
							+ ", title "
					   + "FROM classinfo "
					   + "INNER JOIN comm_code ar ON region = ar.cd_code AND ar.cd_type = 'AR' "
					   + "INNER JOIN comm_code ct ON category = ct.cd_code AND ct.cd_type = 'CT' "
					   + "WHERE trim(ct.cd_name) = ? "
					   + "AND ar.cd_name = ? "
					   + "AND TO_CHAR(class_start, 'YYYYMMDD') >= ? "
					   + "AND TO_CHAR(class_over, 'YYYYMMDD') <= ? "
					   + "AND acceptstatus IS NULL ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);			
			pstmt.setString(2, region);
			pstmt.setString(3, class_start);
			pstmt.setString(4, class_over);
						
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String str = rs.getString("classnum") + "\t";
				str += rs.getString("category") + "\t";
				str += rs.getString("region") + "\t";
				str += rs.getString("class_start") + "\t";
				str += rs.getString("class_over") + "\t";
				str += rs.getString("title");
				System.out.println(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}
	
	public int searchFilterCount(String category, String region, String class_start, String class_over) {
		int cnt = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
					
			String sql = "SELECT classnum "
							+ ", ct.cd_name as category "
							+ ", ar.cd_name as region "
							+ ", TO_CHAR(class_start, 'YYYY/MM/DD') AS class_start "
							+ ", TO_CHAR(class_over, 'YYYY/MM/DD') AS class_over "
							+ ", title "
					   + "FROM classinfo "
					   + "INNER JOIN comm_code ar ON region = ar.cd_code AND ar.cd_type = 'AR' "
					   + "INNER JOIN comm_code ct ON category = ct.cd_code AND ct.cd_type = 'CT' "
					   + "WHERE trim(ct.cd_name) = ? "
					   + "AND ar.cd_name = ? "
					   + "AND TO_CHAR(class_start, 'YYYYMMDD') >= ? "
					   + "AND TO_CHAR(class_over, 'YYYYMMDD') <= ? "
					   + "AND acceptstatus IS NULL ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, region);
			pstmt.setString(3, class_start);
			pstmt.setString(4, class_over);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return cnt;
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



	
	
}
