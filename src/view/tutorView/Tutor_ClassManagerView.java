package view.tutorView;

import java.util.List;
import java.util.Scanner;

import dao.tutorDao.Tutor_ClassManager;
import singleton.Singleton;
import vo.classVO.ClassInfo;

public class Tutor_ClassManagerView {
	Scanner sc = new Scanner(System.in);
	Tutor_ClassManager tcm = new Tutor_ClassManager();
	
	String select = null;
	
	public void classManagerMenuView() {
		Singleton si = Singleton.getInstance();
		String loginId = si.getMlist().get(0).getId();
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 수업 관리 페이지입니다 :::");
			System.out.print("\t" + "1.수업 요청 내역\n"
						    +"\t" + "2.수업 등록\n"
						    +"\t" + "3.수업 스케줄\n"
						    +"\t" + "4.수업 확인\n"
						    +"\t" + "5.수업 수정\n"
						    +"\t" + "6.수업 삭제\n"
						    +"\t" + "0.이전으로\n"
						    +"메뉴 선택 >> ");
			select = sc.nextLine();

			switch (select) {
			
			case "1" :	// 수업 요청 내역
				if (requestView(loginId) > 0) {
					requestManageView();
				}
				break;
				
			case "2" :	// 수업 등록
				registerClassView(loginId);
				break;
				
			case "3" :	// 수업 스케줄
				scheduleMenuView(loginId);
				break;
				
			case "4" :	// 수업 확인
				showRegisteredClassView(loginId);
				break;
				
			case "5" :	// 수업 수정
				showRegisteredClassView(loginId);
				updateClassView(loginId);
				break;
				
			case "6" :	// 수업 삭제
				showRegisteredClassView(loginId);
				deleteClass(loginId);
				break;
				
			case "0" :
				System.out.println("[이전으로]");
				exit = true;
				break;
				
			default :
				System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
				break;
			}

		}
	}

	/////////// 수업 요청내역
	
	public int requestView(String loginId) {
		List<ClassInfo> list = tcm.selectStatus(loginId);
		System.out.println();
		System.out.println("::: 수업 요청 내역 페이지입니다 :::");
		System.out.println("검색 결과 >> " + list.size() + "건의 수업을 학생으로부터 요청받았습니다");
		
		if (list.size() == 0) {
			return 0;
		}
		else {
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println("상태\t" + "번호\t" + "학생ID\t" + "시작일\t\t" + "종료일\t\t" + "제목\t");
			System.out.println("------------------------------------------------------------------------------------------");
			
			for (int i = 0; i < list.size(); i++) 
			{
				System.out.print(list.get(i).getCd_name() + "\t");
				System.out.print(list.get(i).getClassNum() + "\t");
				System.out.print(list.get(i).getStudent_id() + "\t");
				System.out.print(list.get(i).getClass_start() + "\t");
				System.out.print(list.get(i).getClass_over() + "\t");
				System.out.print(list.get(i).getTitle() + "\n");
			}
			System.out.println("------------------------------------------------------------------------------------------");			
		}
		return list.size();
	}
	
	public void requestManageView() {		
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("\t" + "1.요청 승인\n"
							+ "\t" + "2.요청 거절\n"
							+ "\t" + "0.이전으로");
			System.out.print("메뉴 선택 >> ");
			select = sc.nextLine();
	
			String classNum = null;
			switch (select) {
			case "1" :	// 요청 승인
				while (true) {
					System.out.println();
					System.out.println("승인할 수업번호를 입력하세요 (이전으로 이동하려면 0 입력)");
					System.out.print(">> ");
					classNum = sc.nextLine();
					if ("0".equals(classNum)) {
						System.out.println("[이전으로]");
						break;
					}
					int result = tcm.updateStatus(classNum, "03");
					if(result == 0) {
						System.out.println("[입력오류]번호를 다시 확인해주세요");
						continue;
					} else {
						System.out.println("[승인완료]축하합니다! 수업이 매칭되었습니다");
						exit = true;
						break;
					}
				}
				break;
			case "2" :	// 요청 거절
				while (true) {
					System.out.println();
					System.out.println("거절할 수업번호를 입력하세요 (이전으로 이동하려면 0 입력)");
					System.out.print(">> ");
					classNum = sc.nextLine();
					if ("0".equals(classNum)) {
						System.out.println("[이전으로]");
						break;
					}
					int result = tcm.updateStatus(classNum, "02");
					if(result == 0) {
						System.out.println("[입력오류]번호를 다시 확인해주세요");
						continue;
					} else {
						System.out.println("[거절완료]요청을 거절하였습니다. 이제 다른 학생의 승인 요청을 받을 수 있습니다.");
						exit = true;
						break;
					}
				}
				break;
			case "0" :
				System.out.println("[이전으로]");
				exit = true;
				break;	
			default:
				System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
				break;
			}
		}
	}	
	
	
	/////////// 수업등록
	
	public boolean goback(String str) {
		if ("0".equals(str)) {
			System.out.println("[이전으로]");
			return true;
		}
		return false;
	}
	
	public void registerClassView(String loginId) {
		System.out.println();
		System.out.println("::: 수업 등록 페이지입니다 (이전으로 이동하려면 0 입력) :::");
		
		while(true) 
		{
			System.out.println();
			System.out.print("지역 [서울(01)|경기(02)|부산(03)|강원(04)|충청(05)|전라(06)|경상(07)] >> ");
			String region = sc.nextLine();
			if (goback(region)) return;	
			//코드 추가 부분		
			if (!region.equals("01") && !region.equals("02") && !region.equals("03") && !region.equals("04") && 
					!region.equals("05") && !region.equals("06") && !region.equals("07")) {
				System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
				continue;
			}
			//코드 추가 완료			
			
			System.out.print("카테고리 [외국어(01)|디자인/개발(02)|취미/공예(03)|뷰티/헬스(04)|금융/경제(05)|리빙/인테리어(06)] >> ");
			String category = sc.nextLine();
			if (goback(category)) return;
			//코드 추가 부분		
			if (!category.equals("01") && !category.equals("02") && !category.equals("03") && 
					!category.equals("04") && !category.equals("05") && !category.equals("06")) {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			//코드 추가 완료
			
			System.out.print("수업 제목 >> ");
			String title = sc.nextLine();
			
			if (goback(title)) return;
			
			System.out.print("수업시작일(ex_20201228) >> ");
			String class_start = sc.nextLine();
			
			if (goback(class_start)) return;
			
			System.out.print("수업종료일(ex_20201231) >> ");
			String class_over = sc.nextLine();
			
			if (goback(class_over)) return;
			
			System.out.print("가격 >> ");
			String price = sc.nextLine();
			
			if (goback(price)) return;
			
			System.out.print("수업 한줄 소개 >> ");
			String intro = sc.nextLine();
			
			if (goback(intro)) return;
			
			ClassInfo ci = new ClassInfo(region, category, title, class_start, class_over, price, intro, 3);
			int result = tcm.insertStatus(ci, loginId);
			
			if(result == 0) {
				System.out.println("[입력오류]항목을 필수 예시에 맞게 다시 입력해주세요.");
				continue;
			}
			else {
				System.out.println("[등록완료] "+ result + "건이 등록 완료되었습니다.");
				break;
			}
		}
	}		
	
	
	/////////// 수업 스케줄
	public void scheduleMenuView(String loginId) {
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 수업 스케줄 페이지입니다 :::");
			System.out.print("\t" + "1.현재 진행중인 수업\n"
		    				+"\t" + "2.지난 수업\n"
		    				+"\t" + "3.예정된 수업\n"
		    				+"\t" + "0.이전으로\n"
		    				+"메뉴 선택 >> ");
			select = sc.nextLine();
			
			switch (select) {
			case "1" :	// 현재 진행중인 수업
				classListView(loginId, 0);
				break;
			case "2" :	// 지나간 수업
				classListView(loginId, 1);
				break;
			case "3" :	// 예정된 수업
				classListView(loginId, 2);
				break;	
			case "0" :
				System.out.println("[이전으로]");
				exit = true;
				break;
			default:
				System.out.println("[입력 오류]메뉴의 숫자를 확인 후 재입력해주세요");
				break;
			}
		}	
	}

	public void classListView(String loginId, int number) {
		List<ClassInfo> list = tcm.classList(number, loginId);
		String status[] = {"진행중입니다.", "완료되었습니다.", "예정되어 있습니다."};
		String status2[] = {"수업중", "수업완료", "수업예정"};
		System.out.println("검색 결과 >> 총 " + list.size() + "건의 수업이 " + status[number]);
		
		if (list.size() > 0) {
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println("상태\t" + "번호\t" + "학생ID\t" + "시작일\t\t" + "종료일\t\t" + "제목\t");
			System.out.println("------------------------------------------------------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				String msg = status2[number] + "\t"
							+ list.get(i).getClassNum() + "\t"
							+ list.get(i).getStudent_id() + "\t"
							+ list.get(i).getClass_start() + "\t"
							+ list.get(i).getClass_over() + "\t"
							+ list.get(i).getTitle() + "\t";
				System.out.println(msg);
			}
			System.out.println("------------------------------------------------------------------------------------------");
		}
	}
	
	/////////// 수업 확인
	public void showRegisteredClassView(String loginId) {
		List<ClassInfo> list = tcm.selectClass(loginId);
		System.out.println("검색 결과 >> 총 " + list.size() + "건의 강의\n");
		if(list.size() > 0) 
		{
			for (int i = 0; i < list.size(); i++) {
				System.out.println("[수업번호] " + list.get(i).getClassNum());
				System.out.println("[지역] " + list.get(i).getArea());
				System.out.println("[카테고리] " + list.get(i).getSort());
				System.out.println("[수업이름] " + list.get(i).getTitle());
				System.out.println("[수업 한줄소개] " + list.get(i).getIntro());
				System.out.println("[수업시작일] " + list.get(i).getClass_start());
				System.out.println("[수업종료일] " + list.get(i).getClass_over());
					String student_id = list.get(i).getStudent_id();
					if (student_id == null) student_id = "해당사항 없음";
				System.out.println("[학생아이디] " + student_id);
					String status = list.get(i).getStatus();
					if (status == null) status = "해당사항 없음";
				System.out.println("[승인상태] " + status);
				System.out.println("=============================================");	
			}
		}
	}
	
	
	/////////// 수업 수정
	public void updateClassView(String loginId) {
		System.out.println();
		System.out.println("::: 수업정보 수정 페이지입니다(이전으로 이동하려면 0 입력) :::");
		
		while(true) 
		{
			System.out.println();
			System.out.print("수정할 수업 번호: ");
			String classNum = sc.nextLine();
			if (goback(classNum)) return;
			
			List<ClassInfo> listN = tcm.classCheck(classNum, 0);
			List<ClassInfo> listC = tcm.classCheck(classNum, 2);
			if(listN.size() > 0 || listC.size() > 0) {
				System.out.println("[수정불가]이미 매칭되어 진행중이거나 예정된 수업은 정보를 변경할 수 없습니다.");
				continue;
			}
			System.out.print("지역 [서울(01)|경기(02)|부산(03)|강원(04)|충청(05)|전라(06)|경상(07)] >> ");
			String region = sc.nextLine();
			if (goback(region)) return;
			//코드 추가 부분			
			if (!region.equals("01") && !region.equals("02") && !region.equals("03") && !region.equals("04") && 
					!region.equals("05") && !region.equals("06") && !region.equals("07")) {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			//코드 추가 완료		
						
			System.out.print("카테고리 [외국어(01)|디자인/개발(02)|취미/공예(03)|뷰티/헬스(04)|금융/경제(05)|리빙/인테리어(06)] >> ");
			String category = sc.nextLine();
			if (goback(category)) return;
			//코드 추가 부분		
			if (!category.equals("01") && !category.equals("02") && !category.equals("03") && 
					!category.equals("04") && !category.equals("05") && !category.equals("06")) {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			//코드 추가 완료
			
			System.out.print("수업 제목 >> ");
			String title = sc.nextLine();
			if (goback(title)) return;
			
			System.out.print("수업시작일(ex_20201228) >> ");
			String class_start = sc.nextLine();
			if (goback(class_start)) return;
			
			System.out.print("수업종료일(ex_20201231) >> ");
			String class_over = sc.nextLine();
			if (goback(class_over)) return;
			
			System.out.print("가격 >> ");
			String price = sc.nextLine();
			if (goback(price)) return;
			
			System.out.print("수업 한줄 소개 >> ");
			String intro = sc.nextLine();
			if (goback(intro)) return;
			
			ClassInfo ci = new ClassInfo();
			ci.updateClass(classNum, region, category, title, class_start, class_over, price, intro);
			int result = tcm.updateClass(ci, loginId);
			if(result == 0) {
				System.out.println("[입력오류]항목을 필수 예시에 맞게 다시 입력해주세요.");
				continue;
			} 
			else {
				System.out.println("[수정완료] " + result + "건이 수정 완료되었습니다.");
				break;
			}
		}
	}

	
	/////////// 수업 삭제
	public void deleteClass(String loginId) {
		System.out.println();
		System.out.println("::: 수업 삭제 페이지입니다(이전으로 이동하려면 0 입력) :::");
		
		ClassInfo ci = new ClassInfo();
		while(true) 
		{
			System.out.print("삭제할 수업 번호 >> ");
			String classNum = sc.nextLine();
			ci.setClassNum(classNum);
			if (goback(classNum)) return;
			
			List<ClassInfo> listN = tcm.classCheck(classNum, 0);
			List<ClassInfo> listC = tcm.classCheck(classNum, 2);
				
			if(listN.size() > 0 || listC.size() > 0) {
				System.out.println("[수정불가]이미 매칭되어 진행중이거나 예정된 수업은 정보를 변경할 수 없습니다.");
				continue;
			}
			
			System.out.print("수업을 삭제하시겠습니까?[y/n] >> ");
			String yesno_check = sc.nextLine();
			if (goback(yesno_check)) return;
			
			if(yesno_check.equals("n") || yesno_check.equals("N")) 
			{
				System.out.println("이전으로 돌아갑니다.");
				return;
			} 
			else if (yesno_check.equals("y") || yesno_check.equals("Y")) 
			{
				int result = tcm.deleteclass(ci, loginId);
				if(result == 0) 
				{
					System.out.println("[입력오류] 삭제할 데이터가 없습니다. 뒤로 돌아갑니다.");
					return;
				} 
				else 
				{
				System.out.println("[삭제완료] 삭제 건수: "+ result + "건");
				System.out.println("수업이 삭제되었습니다.");
				break;
				}
			} 
			else 
			{
				System.out.println("[입력오류] 알파벳 확인 후 다시 입력해주세요. ");
			}
		}
	}
	
}
