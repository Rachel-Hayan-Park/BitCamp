package main;

import view.LoginJoinView;
import view.studentView.Student_MainView;
import view.tutorView.Tutor_MainView;
import vo.memberVO.Member;

public class mainClass {

	public static void main(String[] args) {

		LoginJoinView ljv = new LoginJoinView();
		Student_MainView smain = new Student_MainView();
		Tutor_MainView tmain = new Tutor_MainView();
		
		while (true) {
			Member member = ljv.loginOrJoinView();	// 로그인한 아이디 get
			
			if (member == null) break;	// 프로그램 종료
			
			if ("01".equals(member.getPosition())) { // 학생일 경우  학생 메인으로 접속
				smain.mainView(member.getId());
			}
			else if ("02".equals(member.getPosition())){	// 튜터일 경우 튜터 메인으로 접속
				tmain.mainView(member.getId());
			}
		}
		
	}

}
