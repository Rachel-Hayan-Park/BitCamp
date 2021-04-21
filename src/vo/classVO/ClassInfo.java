package vo.classVO;

public class ClassInfo {
	private String classNum;
	private String region;
	private String category;
	private String title;
	private String class_start;
	private String class_over;
	private String price;
	private String intro;
	private String tutor_id;
	private String student_id;
	private String acceptStatus;
	private String exp;
	private String cd_name;
	private String area;
	private String sort;
	private String status;
	
	public ClassInfo() {};
	
	public ClassInfo(String classNum, String region, String category, String title, String class_start,
			String class_over, String price, String intro, String tutor_id, String exp) {
		super();
		this.classNum = classNum;
		this.region = region;
		this.category = category;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.price = price;
		this.intro = intro;
		this.tutor_id = tutor_id;
		this.exp = exp;
	}

	public ClassInfo(String classNum, String title, String class_start, String class_over, String student_id) {
		super();
		this.classNum = classNum;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.student_id = student_id;
	}
	
	// String 7개
	public ClassInfo(String classNum, String title, String class_start, String class_over, String student_id,
			String acceptStatus, String cd_name) {
		super();
		this.classNum = classNum;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.student_id = student_id;
		this.acceptStatus = acceptStatus;
		this.cd_name = cd_name;
	}
	
	public void applyResult(String acceptStats, String classNum, String class_start, String class_over
			, String title) {
		this.acceptStatus = acceptStats;
		this.classNum = classNum;
		this.class_start = class_start;
		this.class_over = class_over;
		this.title = title;
	}

	public void classList(String classNum, String title, String class_start, String class_over, String tutor_id) {
		this.classNum = classNum;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.tutor_id = tutor_id;
	}

	public ClassInfo(String classNum, String region, String category, String title, String class_start,
			String class_over) {
		this.classNum = classNum;
		this.region = region;
		this.category = category;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
	}

	
	// Register Class 
	public ClassInfo(String region, String category, String title, String class_start, String class_over, String price,
			String intro, int number) {
		this.region = region;
		this.category = category;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.price = price;
		this.intro = intro;
	}
	
	// ClassManager Class
	public void selectClass(String classNum, String region, String area, String category, 
			String sort, String title, String intro, String class_start, String class_over,
			String price, String student_id, String acceptStatus, String status) {
		this.classNum = classNum;
		this.region = region;
		this.category = category;
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.price = price;
		this.intro = intro;
		this.student_id = student_id;
		this.acceptStatus = acceptStatus;
		this.area = area;
		this.sort = sort;
		this.status = status;
	}
	
	// ClassManager Class
	public void updateClass(String classNum, String region, String category, 
			String title, String class_start, String class_over, String price, String intro) {
		this.classNum = classNum;
		this.region = region;
		this.category = category; 
		this.title = title;
		this.class_start = class_start;
		this.class_over = class_over;
		this.price = price;
		this.intro = intro;
	}
	
	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClass_start() {
		return class_start;
	}

	public void setClass_start(String class_start) {
		this.class_start = class_start;
	}

	public String getClass_over() {
		return class_over;
	}

	public void setClass_over(String class_over) {
		this.class_over = class_over;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTutor_id() {
		return tutor_id;
	}

	public void setTutor_id(String tutor_id) {
		this.tutor_id = tutor_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getAcceptStatus() {
		return acceptStatus;
	}

	public void setAcceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
	public String getCd_name() {
		return cd_name;
	}

	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}

	public String getArea() {
		return area;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		String msg = "\t[수업 번호] " + classNum + "\n"
				   + "\t[지역] " + region + "\n"
				   + "\t[카테고리] " + category + "\n"
				   + "\t[주제] " + title + "\n"
				   + "\t[수업 소개] " + intro + "\n"
				   + "\t[시작일] " + class_start + "\n"
				   + "\t[종료일] " + class_over + "\n"
				   + "\t[가격] " + price + "\n"
				   + "\t[튜터 ID] " + tutor_id + "\n"
				   + "\t[튜터 이력] " + exp;	
		
		return msg;
	}
	
	
	
	
}
