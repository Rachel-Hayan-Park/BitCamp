package view;

import java.util.Scanner;

import dao.LoginJoin;
import dao.MyPage;
import singleton.Singleton;
import vo.memberVO.Member;

public class MyPageView {
	Scanner sc = new Scanner(System.in);
	MyPage mp = new MyPage();
	String choice;
	
	public boolean myPageMenuView() {
		Singleton si = Singleton.getInstance();
		LoginJoin lj = new LoginJoin();
		boolean gotoMain = false;
		boolean exit = false;
		
		while (!exit) {
			System.out.println();
			System.out.println("::: 마이 페이지입니다 :::");
			System.out.print("\t" + "1.나의 정보\n"
						    +"\t" + "2.회원 정보 수정\n"
						    +"\t" + "3.회원 탈퇴\n"
						    +"\t" + "0.이전으로\n"
						    +"메뉴 선택 >> ");
			choice = sc.nextLine();
			
			try {
				switch (choice) {
				case "1" :	// 나의 정보
					System.out.println();
					System.out.println("::: 나의 정보 페이지입니다 :::");
					Member member = si.getMlist().get(0);
					lj.select(member);
					break;
					
				case "2" :	// 회원 정보 수정
					idpwCheck(si, "정보 변경");						
					updateMemberView(si);
					break;
					
				case "3" :	// 회원 탈퇴 페이지
					idpwCheck(si, "회원 탈퇴");
					if (deleteMemberView(si)) {
						gotoMain = true;
						exit = true;
						break;
					}
					break;
					
				case "0" :	// 이전으로 이동
					System.out.println("[이전으로]");
					exit = true;
					break;
					
				default :
					System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return gotoMain;
	}

	public void idpwCheck(Singleton si, String str) throws Exception {
		while (true) {
			System.out.println();
			System.out.println("::: " + str + "전 본인 확인을 위한 페이지입니다(이전으로 이동하려면 0 입력) :::");
			System.out.print("\t" + "ID : ");
			String id = sc.nextLine();
			
			if ("0".equals(id)) {
				throw new Exception("[이전으로]");
			}
			System.out.print("\t" + "PW : ");
			String password = sc.nextLine();
			if ("0".equals(password)) {
				throw new Exception("[이전으로]");
			}
			
			Member member = si.getMlist().get(0);
			if (id.equals(member.getId()) && password.equals(member.getPassword())) {
				System.out.println("[정보일치] " + member.getName() + "님의 " + str + " 페이지로 이동합니다.");
				break;
			} else {
				System.out.println("[입력오류]아이디와 비밀번호를 확인후 다시 입력해주세요");
			}
		}
	}
	
	public void updateMemberView(Singleton si) {
		LoginJoin lj = new LoginJoin();
		Member member = si.getMlist().get(0);
		
		String st = null;
		if ("01".equals(member.getPosition())) {
			st = "학생";
		}else if ("02".equals(member.getPosition())) {
			st = "튜터";
		}
		
		while (true) {
			System.out.println();
			System.out.println("::: " + st + " 정보 변경 페이지입니다(이전으로 돌아가려면 0 입력):::");
			
			member = updateDetailView(member, lj);
			if (member == null) {	// 이전으로 돌아가기
				break;
			}
			
			int cnt = mp.updateMember(member);
			if (cnt > 0) {
				System.out.println("\"업데이트가 완료되었습니다\"");
				lj.select(member);
				si.getMlist().set(0, member);
				break;
			}else {
				System.out.println("[업데이트 실패]다시 시도해주세요");
			}
		}
	}
	
	public Member updateDetailView(Member member, LoginJoin lj) {
		
		LoginJoinView ljv = new LoginJoinView();
		
		String password = ljv.inputString(lj, "비밀번호", true, true);
		if ("0".equals(password)) {
			return null;
		}
		String name = ljv.inputString(lj, "성명", true, true);
		if ("0".equals(name)) {
			return null;
		}
		String phone = ljv.inputString(lj, "전화번호", true, false);
		if ("0".equals(phone)) {
			return null;
		}

		if ("02".equals(member.getPosition())) {
			String exp = ljv.inputString(lj, "이력", true, false);
			if ("0".equals(exp)) {
				return null;
			}
			member.setExp(exp);
		}
		
		member.setPassword(password);
		member.setName(name);
		member.setPhone(phone);
		
		return member;
	}
	
	public boolean deleteMemberView(Singleton si) {
		boolean deleteSuccess = false;
		Member member = si.getMlist().get(0);
		
		while (true) {
			System.out.println();
			System.out.print("탈퇴하시면 더이상 튜터링GO의 서비스를 이용하실 수 없습니다.\n탈퇴하시겠습니까?[예(1)/아니오(2)] >> ");
			choice = sc.nextLine();
			if ("1".equals(choice)) {
				// 탈퇴 메소드
				if("02".equals(member.getPosition())) {	// 튜터가 현재 진행중인 수업이 있는데 탈퇴할 때
					if (mp.deleteInfoTutor(member.getId()) > 0) {
						System.out.println("[탈퇴 미승인] " + member.getName() + " 튜터님은 현재 수업이 진행중이므로 탈퇴가 불가능합니다.");
						continue;
					}
				}else {	// 학생이 현재 수강중인 수업이 있는데 탈퇴할 때
					if (mp.deleteInfoStudent(member.getId()) > 0) {
						System.out.println("[탈퇴 미승인] " + member.getName() + "님은 현재 수강중인 수업이 있어 탈퇴가 불가능합니다.");
						continue;
					}
				}
				mp.deleteMember(member);
				si.getMlist().clear();
				System.out.println("[탈퇴완료]\n\t\"그동안 이용해주셔서 감사드립니다\"");
				deleteSuccess = true;
				break;
			}
			else if ("2".equals(choice)) {
				System.out.println("[마이페이지로]");
				return false;
			}else {
				System.out.println("[입력오류]메뉴의 숫자를 확인 후 재입력해주세요");
			}
		}
		return deleteSuccess;
	}

}
