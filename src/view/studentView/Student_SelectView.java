package view.studentView;

import java.util.Scanner;

import dao.studentDao.Student_Select;
import singleton.Singleton;
import vo.classVO.ClassInfo;

public class Student_SelectView {
	Scanner sc = new Scanner(System.in);
	String choice;
	Student_Select ss = new Student_Select();
	
	public void selectMenuView() {
		String message = "\t1.전체 조회" + "\n"
				 		 + "\t2.카테고리 보기" + "\n"
				 		 + "\t0.이전으로" + "\n"
				 		 + "메뉴 선택 >> ";
		
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 수업 검색 페이지입니다 :::");
			System.out.print(message);
			choice = sc.nextLine();
			
			switch (choice) {
			case "1" :
				// 전체 조회
				selectAllView();
				selectOneClassView();
				break;
			case "2" :
				// 카테고리 보기
				categoryMenuView();
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
	
	// 전체 조회
	public void selectAllView() {
		System.out.println();
		System.out.println("::: 전체 수업 조회 페이지입니다 :::");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("번호\t" + "지역\t" + "카테고리\t\t" + "시작일\t\t" + "종료일\t\t" + "수업 제목\t\t\t");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		ss.selectAll();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
	}
	
	// 하나의 수업을 골라 자세히 보고싶을 때 
	public void selectOneClassView() {
		ClassInfo ci;
		String classNum;
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("더 자세히 알고 싶은 수업의 번호를 입력하세요(이전으로 이동하려면 0 입력)");
			System.out.print(">> ");
			classNum = sc.nextLine();
			
			if ("0".equals(classNum)) {
				System.out.println("[이전으로]");
				return;
			}else {
				ci = ss.selectOneClass(classNum);
				if (ci == null) {
					System.out.println("[입력 오류]존재하지 않는 번호입니다");
					continue;
				}
				System.out.println(ci.toString());
			}
			
			boolean exit2 = false;
			while (!exit2) {
				System.out.print("담당 튜터에게 승인요청을 보내시겠습니까?[예(1)/아니오(2)] >> ");
				choice = sc.nextLine();
				switch (choice) {
				case "1" :
					// 승인 요청 페이지
					if (applyClassView(ci, classNum)) {	// 승인 요청 완료 되었을 때
						exit2 = true;
						while (true) {
							System.out.print("계속해서 수업을 고르시겠습니까?[예(1)/아니오(2)] >> ");
							choice = sc.nextLine();
							if ("1".equals(choice)) { // 자세히 보고 싶은 수업 계속 고르기
								System.out.println();
								break;
							}else if ("2".equals(choice)) {
								System.out.println("[학생메인으로 이동]");
								exit = true;
								break;
							}else {
								System.out.println("[입력 오류]번호를 확인 후 다시 입력해주세요");
							}
						}
					}else { // 승인 요청이 시스템상 실패할 때
						System.out.println("[승인 요청 실패]다시 시도해주세요");
						continue;
					}					
					break;
				case "2" :
					System.out.println("[이전으로]");	// 자세히 보고 싶은 수업 고르기로 이동
					exit2 = true;
					break;
				default :
					System.out.println("[입력 오류]번호를 확인 후 다시 입력해주세요");
					break;
				}
			}
			
		}
	}
	
	// 수업을 고른 뒤 승인 요청할때
	public boolean applyClassView(ClassInfo ci, String classNum) {
		boolean applySuccess = false;
		Singleton si = Singleton.getInstance();
		int cnt = ss.applyClass(si.getMlist(), classNum);
		if (cnt > 0) {
			System.out.println("[승인요청 완료]\n *승인 여부는 '학생 메인'의 '2.나의 수업 관리 > 1.수업 신청 내역'에서 확인할 수 있습니다");
			applySuccess = true;
		}else {
			System.out.println("[시스템 오류]다시 시도해주세요");
		}
		return applySuccess;
	}

	// 카테고리 보기 
	public void categoryMenuView() {
		String message = "\t1.외국어 " + "\n"
		 		 + "\t2.디자인/개발 " + "\n"
		 		 + "\t3.취미/공예 " + "\n"
		 		 + "\t4.뷰티/헬스" + "\n"
		 		 + "\t5.금융/경제" + "\n"
		 		 + "\t6.리빙/인테리어" + "\n"
		 		 + "\t0.이전으로" + "\n"
		 		 + "메뉴 선택 >> ";
		
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("::: 카테고리로 쉽게 수업을 찾아보세요 :::");
			System.out.print(message);
			choice = sc.nextLine();
			
			switch (choice) {
			case "1" :
				if (searchFilterView("외국어")) {
					selectOneClassView();
				}
				break;
			case "2" :
				if (searchFilterView("디자인/개발")){
					selectOneClassView();
				}
				break;
			case "3" :
				if (searchFilterView("취미/공예")) {
					selectOneClassView();
				}
				break;
			case "4" : 
				if (searchFilterView("뷰티/헬스")) {
					selectOneClassView();
				}
				break;
			case "5" :
				if (searchFilterView("금융/경제")) {
					selectOneClassView();
				}
				break;
			case "6" :
				if (searchFilterView("리빙/인테리어")) {
					selectOneClassView();
				}
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
	
	
	// 필터 검색 (지역, 시간 입력후 원하는 결과 찾기)
	public boolean searchFilterView(String category) {
		boolean isNotEmpty = false;
		
		String str = "| 카테고리: " + category + " | ";
		String region = null;
		String class_start = null;
		String class_over = null;
		String class_start2 = null;
		String class_over2 = null;
		
		System.out.println(str);
		
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("수업이 진행될 지역을 설정하세요");
			System.out.println("\t" + "-필수 예시 [서울/경기/부산/강원/충청/전라/경상 중 택일 (이전으로 이동하려면 0 입력)]");
			System.out.print("지역 입력 >> ");
			region = sc.nextLine();
			
			if ("0".equals(region)) {
				System.out.println("[이전으로]");
				return false;
			}
			if (!"서울".equals(region) && !"경기".equals(region) && !"부산".equals(region) && !"강원".equals(region)
					&& !"충청".equals(region) && !"전라".equals(region) && !"경상".equals(region)) {
				System.out.println("[입력오류]필수 예시에 있는 지역명을 확인 후 다시 입력해주세요");
				continue;
			}else {
				str += "지역: " + region + " | ";
				System.out.println(str);
			}
			
			while (!exit) {
				System.out.println();
				System.out.println("수업의 희망 시작일을 설정하세요");
				System.out.println("\t" + "-필수 예시 [2020년 2월 13일 이후-> 20200213 처럼 숫자만 입력(이전으로 이동하려면 0 입력)]");
				System.out.print("날짜 입력 >> ");
				class_start = sc.nextLine();
				if ("0".equals(class_start)) {
					System.out.println("[이전으로]");
					break;
				}
				try {
					if (class_start.length() != 8) {
						System.out.println("[입력오류]필수 예시에 알맞게 다시 입력해주세요");
						continue;
					}else if (!(Integer.parseInt(class_start) + "").equals(class_start)) {
						System.out.println("[입력오류]문자열이 포함되지 않은 숫자의 형태로 입력해주세요");
						continue;
					}else {
						class_start2 = class_start.substring(0, 4) + "/" + class_start.substring(4, 6) + "/"
									+ class_start.substring(6, 8);						
						str += "시작일: " + class_start2 + " 이후 | ";
						System.out.println(str);
					}
				} catch (NumberFormatException e) {
					System.out.println("[입력오류]문자열이 포함되지 않은 숫자의 형태로 입력해주세요");
					continue;
				}
				
				while (true) {
					System.out.println();
					System.out.println("수업의 희망 종료일을 설정하세요");
					System.out.println("\t" + "-필수 예시 [2020년 3월 16일 이전-> 20200316 처럼 숫자만 입력(이전으로 이동하려면 0 입력)]");
					System.out.print("날짜 입력 >> ");
					class_over = sc.nextLine();
					try {
						if ("0".equals(class_over)) {
							System.out.println("[이전으로]");
							break;
						}
						if (class_over.length() != 8) {
							System.out.println("[입력오류]필수 예시에 알맞게 다시 입력해주세요");
							continue;
						}else if (!(Integer.parseInt(class_over) + "").equals(class_over)) {
							System.out.println("[입력오류]문자열이 포함되지 않은 숫자의 형태로 입력해주세요");
							continue;
						}else {
							class_over2 = class_over.substring(0, 4) + "/" + class_over.substring(4, 6) + "/"
										+ class_over.substring(6, 8);						
							str += "종료일: " + class_over2 + " 이전 | ";
							System.out.println(str);
							exit = true;
							break;
						}
					} catch (NumberFormatException e) {
						System.out.println("[입력오류]문자열이 포함되지 않은 숫자의 형태로 입력해주세요");
						continue;
					}
				}
			}
		}
//		| 카테고리: 외국어 | 지역: 서울 | 시작일: 2020/12/24 이후 | 종료일: 2021/04/28 이전 | <- 이 모양으로 출력됨
		System.out.println();
		int cnt = ss.searchFilterCount(category, region, class_start, class_over);
		System.out.println("필터 검색 결과 >> 총 " + cnt + "건의 수업이 있습니다");
		if (cnt > 0) {
			String msg = "번호\t" + "카테고리\t\t" + "지역\t" + "시작일\t\t" + "종료일\t\t" + "제목\t";
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println(msg);
			System.out.println("------------------------------------------------------------------------------------------");
			ss.searchFilter(category, region, class_start, class_over);
			System.out.println("------------------------------------------------------------------------------------------");
			isNotEmpty = true;
		}
		return isNotEmpty;
	}
	
	
	
}
