package view.tutorView;

import java.util.Scanner;

import dao.LoginJoin;
import view.MyPageView;

public class Tutor_MainView {
	Scanner scanner = new Scanner(System.in);
	MyPageView mpv;
	Tutor_ClassManagerView tcv;
	String select;
	
	public Tutor_MainView() {
		mpv = new MyPageView();
		tcv = new Tutor_ClassManagerView();
	}
	
	public void mainView(String loginId) {
		LoginJoin lj = new LoginJoin();
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 튜터 메인 페이지입니다 :::");
			System.out.print("\t" + "1.수업 관리\n"
						    +"\t" + "2.마이 페이지\n"	
							+"\t" + "0.로그아웃 후 메인으로 이동\n"
						    +"메뉴 선택 >> ");
			select = scanner.nextLine();
			
			switch (select) {
			
			case "1" :	// 수업 관리
				tcv.classManagerMenuView();
				break;
				
			case "2" :	// 마이페이지
				if(mpv.myPageMenuView()) { // 메인으로
					System.out.println("[메인으로]");
					exit = true;
				}
				break;
				
			case "0" :	// 로그아웃 후 메인으로 이동
				if (lj.logout(loginId)) {
					System.out.println("[메인으로]");
					exit = true;
				}
				break;
				
			default :
				System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
				break;
			}
		}
	}
}
