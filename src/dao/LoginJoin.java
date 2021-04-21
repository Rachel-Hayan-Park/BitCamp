package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import singleton.Singleton;
import vo.memberVO.Member;

//로그인, 회원가입이 이루어지는 공간입니다.
public class LoginJoin {
	Scanner sc = new Scanner(System.in);

	final String DRIVER = "oracle.jdbc.OracleDriver"; 
	final String URL = "jdbc:oracle:thin:@192.168.0.121:1521:xe";
	final String USER = "tutoringgo";
	final String PASSWORD = "tutoringgopw";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public LoginJoin() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 연결 오류");
			e.printStackTrace();
		}
	}
	
	public Member login(Member member) {	// 학생, 튜터 로그인 
				
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "" 
					+ "SELECT NAME FROM MEMBER " 
					+ "WHERE ID = ? AND PASSWORD = ? "
					+ "AND joinstatus = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, "01");
			
			rs = pstmt.executeQuery();
						
			if(rs.next()) {	
				member.setName(rs.getString("NAME"));
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return member;
	}
	
	public boolean logout(String id) {
		Singleton si = Singleton.getInstance();
		while (true) {
			System.out.print("로그아웃하시겠습니까?[예(1)/아니오(2)] >> ");
			String choice = sc.next();
			switch (choice) {
			case "1" :
				System.out.println("\"" + si.getMlist().get(0).getName() + "님, 안녕히 가세요.\"");
				si.getMlist().clear();
				return true;
			case "2" :
				return false;
			default :
				System.out.println("[입력 오류]번호를 확인하고 다시 입력해주세요");
				break;
			}
		}
	}
		
	public Member select(Member member) {	// 학생, 튜터 회원정보 검색 
		
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "" 
					+ "SELECT ID, password, NAME, PHONE, POSITION, EXP "
					+ "FROM MEMBER " 
					+ "WHERE ID = ? AND PASSWORD = ? "
					+ "AND joinstatus = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, "01"); 
			
			rs = pstmt.executeQuery();
						
			if (rs.next()) {
				String str = "";
				str += rs.getString("ID") + "\t";
				str += rs.getString("password") + "\t ";
				str += rs.getString("NAME") + "\t";
				str += rs.getString("PHONE") + "\t";
				
				if ("01".equals(rs.getString("position"))) {
					str += "학생" + "\t";
					member.setPosition("01");
					System.out.println("---------------------------------------------");
					System.out.println("아이디\t" + "비밀번호\t " + "성함\t" + "전화번호\t\t" + "타입");
					System.out.println("---------------------------------------------");
					System.out.println(str);
					System.out.println("---------------------------------------------");
				}
				else if ("02".equals(rs.getString("position"))) {
					str += "튜터" + "\t";
					str += rs.getString("EXP") + "\t";
					member.setPosition("02");
					member.setExp(rs.getString("EXP"));
					System.out.println("----------------------------------------------------------------------");
					System.out.println("아이디\t" + "비밀번호\t " + "성함\t" + "전화번호\t\t" + "타입\t" + "이력");
					System.out.println("----------------------------------------------------------------------");
					System.out.println(str);
					System.out.println("----------------------------------------------------------------------");
				}
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return member;
	}

	public int joinStudent(Member student) {	// 학생 등록 
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = ""
					+ "INSERT INTO MEMBER " 
					+ "(ID, PASSWORD, NAME, PHONE, POSITION, JOINSTATUS) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getPassword()); 
			pstmt.setString(3, student.getName());
			pstmt.setString(4, student.getPhone());
			pstmt.setString(5, "01");
			pstmt.setString(6, "01");
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}	
	
	public int joinTutor(Member tutor) {		// 튜터 등록 
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = ""
					+ "INSERT INTO MEMBER " 
					+ "(ID, PASSWORD, NAME, PHONE, EXP, POSITION, JOINSTATUS) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, tutor.getId());
			pstmt.setString(2, tutor.getPassword()); 
			pstmt.setString(3, tutor.getName());
			pstmt.setString(4, tutor.getPhone());
			pstmt.setString(5, tutor.getExp());
			pstmt.setString(6, "02");
			pstmt.setString(7, "01");
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	public boolean idCheck(String id) {
		boolean isIdValid = false;
		
		if (id == null || id == "") {
			return false;
		}
		
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = "" 
					+ "SELECT * FROM MEMBER " 
					+ "WHERE ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
						
			if(rs.next() == false) {	
				isIdValid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return isIdValid;
	}
	
	public boolean isNotNull(String str) {
		boolean isNotNull = false;
		if (str == null || str.trim().length() == 0) {
			System.out.println("---[공백 불허]필수 입력칸입니다---");
		}else {
			isNotNull = true;
		}
		return isNotNull;
	}
	
	public boolean isLengthValid(String str) {
		boolean isLengthValid = false;
		if (str.trim().length() < 2 || str.trim().length() > 7) {
			System.out.println("[입력 길이 미충족]2자 이상 7자 이하로 입력하세요");
		}else {
			isLengthValid = true;
		}
		return isLengthValid;
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
