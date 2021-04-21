package view.studentView;

import java.util.Scanner;
import dao.LoginJoin;
import view.MyPageView;

public class Student_MainView {
	Scanner sc = new Scanner(System.in);
	Student_SelectView ssv;
	Student_ClassManagerView scv;
	MyPageView mpv;
	String choice;
	
	public Student_MainView() {
		ssv = new Student_SelectView();
		scv = new Student_ClassManagerView();
		mpv = new MyPageView();
	}
	
	public void mainView(String id) {
		LoginJoin lj = new LoginJoin();
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 학생 메인 페이지입니다 :::");
			System.out.print("\t" + "1.수업 검색\n"
						    +"\t" + "2.나의 수업 관리\n"
							+"\t" + "3.마이 페이지\n"
						    +"\t" + "0.로그아웃 후 메인으로 이동\n"
						    +"메뉴 선택 >> ");
			choice = sc.nextLine();
			
			switch (choice) {
			case "1" :	// 수업 검색 페이지
				ssv.selectMenuView();
				break;
			
			case "2" :	// 나의 수업 관리 페이지
				scv.classManagerMenuView();
				break;
			
			case "3" :	// 마이 페이지
				if(mpv.myPageMenuView()) { // 메인으로
					System.out.println("[메인으로]");
					exit = true;
				}
				break;
			
			case "0" :	// 로그아웃 후 이전으로 이동
				if (lj.logout(id)) {
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
