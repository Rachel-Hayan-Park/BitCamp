package view.studentView;

import java.util.List;
import java.util.Scanner;

import dao.studentDao.Student_ClassManager;
import singleton.Singleton;
import vo.classVO.ClassInfo;

public class Student_ClassManagerView {
	Scanner sc = new Scanner(System.in);
	Student_ClassManager scm = new Student_ClassManager();
	
	String choice = null;
	
	public void classManagerMenuView() {
		Singleton si = Singleton.getInstance();
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 나의 수업 관리 페이지입니다 :::");
			System.out.print("\t" + "1.수업 신청 내역\n"
						    +"\t" + "2.현재 진행중인 수업\n"
						    +"\t" + "3.지난 수업\n"
						    +"\t" + "4.예정된 수업\n"
						    +"\t" + "0.이전으로\n"
						    +"메뉴 선택 >> ");
			choice = sc.next();
			
			try {
				switch (choice) {
				case "1" :	// 수업 신청 내역
					applyMenuView();
					break;
				case "2" :	// 현재 진행중인 수업
					classListView(si, 0);
					break;
				case "3" :	// 지난 수업
					classListView(si, 1);
					break;
				case "4" :	// 예정된 수업
					classListView(si, 2);
					break;	
				case "0" :
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
	}

	public void applyMenuView() {
		Singleton si = Singleton.getInstance();	
		String name = si.getMlist().get(0).getName();
		String student_id = si.getMlist().get(0).getId();
		List<ClassInfo> list = scm.applyResult(student_id);
		
		System.out.println();
		System.out.println("::: 수업 신청 내역 페이지입니다 :::");
		
		if (list.size() == 0) {	// 신청한 수업 내역이 없을 때
			System.out.println("검색결과 >> 신청 내역이 존재하지 않습니다");
			System.out.println("\t" + name + "님, 아직 마음에 드는 수업을 찾지 못하셨나요? \n"
								+ "\t검색 기능을 통해 다양한 튜터의 수업을 만나보실 수 있습니다.");
			System.out.println("[학생메인으로 이동]");
			return;
		}else {		// 신청한 수업 내역이 존재할 때
			System.out.println("검색결과 >> 총 " + list.size() + "건의 내역이 존재합니다");
			applyResultView(list);
			/*
				시간이 남는다면 기능 추가 --> 신청한 수업 자세히 보기, 신청 취소하기 
			*/
			
			
			
			
			
			
			
			
			
			
		}
	}
	
	public void applyResultView(List<ClassInfo> list) {
		
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("승인여부\t" + "번호\t" + "시작일\t\t" + "종료일\t\t" + "수업 제목\t\t\t");
		System.out.println("--------------------------------------------------------------------------------------");
		
		for (int i = 0; i < list.size(); i++) {
			String msg = list.get(i).getAcceptStatus() + "\t"
					   + list.get(i).getClassNum() + "\t"
					   + list.get(i).getClass_start() + "\t"
					   + list.get(i).getClass_over() + "\t"
					   + list.get(i).getTitle() ;
			System.out.println(msg);
		}
		
		System.out.println("--------------------------------------------------------------------------------------");
	}
	
	public void classListView(Singleton si, int number) {
		String loginId = si.getMlist().get(0).getId();
		List<ClassInfo> list = scm.classList(number, loginId);
		String status[] = {"진행중입니다.", "완료되었습니다.", "예정되어 있습니다."};
		String status2[] = {"수강중", "수강완료", "수강예정"};
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
							+ list.get(i).getTitle();
				System.out.println(msg);
			}
			System.out.println("------------------------------------------------------------------------------------------");
		}
	}
	
}
