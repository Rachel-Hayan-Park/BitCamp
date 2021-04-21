package view;

import java.util.Scanner;

import dao.LoginJoin;
import singleton.Singleton;
import vo.memberVO.Member;

// 로그인, 회원가입 뷰 
public class LoginJoinView {
	Scanner sc = new Scanner(System.in);
	LoginJoin lj = new LoginJoin();
	Member member = null;
	String choice;
	
	public Member loginOrJoinView() {
		
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 튜터링GO! 과외수업 매칭 서비스 :::");
			System.out.println("\t1.로그인" + "\n"
							 + "\t2.회원가입" + "\n"
							 + "\t0.종료");
			System.out.print("메뉴 선택 >> ");
			choice = sc.nextLine();
			
			switch (choice) {
			case "1" :
				member = loginView();
				if (member == null) {
					break;
				}
				exit = true;
				break;
			case "2" :
				member = joinView();
				if (member == null) break;
				exit = true;
				break;
			case "0" :
				System.out.println("[프로그램 종료]");
				return null;
			default :
				System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
				break;
			}
		}
		return member;
	}
	
	private Member joinView() {
		Member member = null;
		
		String message = "\t1.학생 등록" + "\n"
				 		 + "\t2.튜터 등록" + "\n"
				 		 + "\t0.이전으로" + "\n"
				 		 + "메뉴 선택 >> ";
		
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 회원가입 페이지입니다 :::");
			System.out.print(message);
			choice = sc.nextLine();
			
			switch (choice) {
				case "1" : {
					if (!joinViewS()) { // 학생 등록 뷰
						continue;
					}
					member = loginView();
					if (member == null) break;
					exit = true;
					break;
				}
				case "2" : {
					if (!joinViewT()) {; // 튜터 등록 뷰
						continue;
					}
					member = loginView();
					if (member == null) break;
					exit = true;
					break;
				}
				case "0": {
					System.out.println("[이전으로]");
					exit = true;
					break;
				}
				default : {
					System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
				}
			}
		}
		return member;
	}

	private Member loginView() {
		LoginJoin lj = new LoginJoin();
		Member member = null;
		Singleton si = Singleton.getInstance();
		
		while (true) {
			System.out.println();
			System.out.println("::: 로그인 페이지입니다(이전으로 이동하려면 0 입력) :::");
			System.out.print("\t" + "ID : ");
			String id = sc.nextLine();
			
			if ("0".equals(id)) {
				System.out.println("[이전으로]");
				return null;
			}
			
			System.out.print("\t" + "PW : ");
			String password = sc.nextLine();
			
			
			if ("0".equals(password)) {
				System.out.println("[이전으로]");
				return null;
			}
			member = new Member(id, password);
			member = lj.login(member);
			
			if (member == null) {
				System.out.println("[입력 오류]아이디와 비밀번호를 확인 후 다시 입력해주세요");
			}
			else {
				System.out.println("\"" + member.getName() + "님, 반갑습니다!\"");
				member = lj.select(member);
				si.getMlist().add(member);
				break;
			}
		}
		return member;
	}
	
	private boolean joinViewS() {	// 학생 등록 뷰 
		
		LoginJoin lj = new LoginJoin();
		Member member = null;
		
		System.out.println();
		System.out.println("::: 학생 등록 페이지입니다(이전으로 이동하려면 0 입력) :::");
		member = joinColumnView(lj, member, "student");
			
		if (member == null) {
			System.out.println("[이전으로]");
			return false;
		}
		
		return joinCheckView(lj, member, "student");
	}

	private boolean joinViewT() {	// 튜터 등록 뷰 
		boolean joinSuccess = false;
		
		LoginJoin lj = new LoginJoin();
		Member member = null;
		
		System.out.println();
		System.out.println("::: 튜터 등록 페이지입니다(이전으로 이동하려면 0 입력) :::");
		member = joinColumnView(lj, member, "tutor");
		
		if (member == null) {
			System.out.println("[이전으로]");
			return joinSuccess;
		}

		if (joinCheckView(lj, member, "tutor")) {	// 회원가입 완료되면 true, 돌아가기 눌렀으면 false
			joinSuccess = true;
		}
		
		return joinSuccess;
	}
	
	
	public boolean idCheckView(String id) {
		LoginJoin lj = new LoginJoin();
		boolean isIdValid = false;
		
		if (lj.idCheck(id)) {
			System.out.println("\"[사용가능]멋진 ID군요!\"");
			isIdValid = true;
		}else {
			System.out.println("\"[사용불가]이미 사용중인 ID입니다.\"");
		}
		return isIdValid;
	}
	
	
	public String inputString(LoginJoin lj, String columnName, boolean checkNotNull, boolean checkLength) {
		String result = null;
		while (true) {
			System.out.printf("\t%s : ", columnName);
			result = sc.nextLine();
			
			if ("0".equals(result)) {
				return result;
			}
			if (checkNotNull && !lj.isNotNull(result)) {
				continue;
			}
			if (checkLength && !lj.isLengthValid(result)) {
				continue;
			}
			return result;
		}
	}

	
	public Member joinColumnView(LoginJoin lj, Member member, String st) {
		String id = null;
		String password = null;
		String name = null;
		String phone = null;
		String exp = null;
		
		while (true) {
			id = inputString(lj, "아이디", true, true);
			if ("0".equals(id)) {
				return null;
			}
			if (idCheckView(id)) { // id 중복 체크
				break;
			}
		}
		password = inputString(lj, "비밀번호", true, true);
		if ("0".equals(password)) {
			return null;
		}
		name = inputString(lj, "성명", true, true);
		if ("0".equals(name)) {
			return null;
		}
		phone = inputString(lj, "전화번호", true, false);
		if ("0".equals(phone)) {
			return null;
		}

		if ("student".equals(st)) {
			member = new Member(id, password, name, phone);
		}
		else if("tutor".equals(st)) {
			exp = inputString(lj, "이력", true, false);
			if ("0".equals(exp)) {
				return null;
			}
			member = new Member(id, password, name, phone, exp);
		}
		return member;
	}
	
	
	public boolean joinCheckView(LoginJoin lj, Member member, String st) {
		while (true) {
			System.out.print("가입하시겠습니까?[예(1)/아니오(2)] >> ");
			choice = sc.nextLine();
			
			if ("1".equals(choice)) {
				if ("student".equals(st)) {
					lj.joinStudent(member);
				}else if("tutor".equals(st)) {
					lj.joinTutor(member);
				}
				lj.select(member);
				System.out.println("[가입 완료]로그인 화면으로 이동합니다");
				break;
			}
			else if ("2".equals(choice)) {
				System.out.println("[가입 취소]이전 페이지로 이동합니다");
				return false;
			}else {
				System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
			}
		}
		return true;
	}

}
