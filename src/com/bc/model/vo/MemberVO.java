package com.bc.model.vo;

public class MemberVO {
	private String id, password, name, year, month, day, phonnum;
	private String location, email, joindate, profile_img, common_name;
	
	public MemberVO() { }	
	
	//마이페이지 - 회원정보수정
	public MemberVO(String password, String phonnum, String location, String email, String profile_img, String id) {
		this.password = password;
		this.phonnum = phonnum;
		this.location = location;
		this.email = email;
		this.profile_img = profile_img;
		this.id = id;
	}
	
	
	public MemberVO(String id, String password, String name, String year, String month, String day, String phonnum,
			String location, String email, String profile_img) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.phonnum = phonnum;
		this.location = location;
		this.email = email;
		this.profile_img = profile_img;
	}

	//마이페이지 - 지역 불러오기
	public MemberVO(String id, String password, String name, String year, String month, String day, String phonnum,
			String location, String email, String joindate, String profile_img, String common_name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.phonnum = phonnum;
		this.location = location;
		this.email = email;
		this.joindate = joindate;
		this.profile_img = profile_img;
		this.common_name = common_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPhonnum() {
		return phonnum;
	}

	public void setPhonnum(String phonnum) {
		this.phonnum = phonnum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	
	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", year=" + year + ", month="
				+ month + ", day=" + day + ", phonnum=" + phonnum + ", location=" + location + ", email=" + email
				+ ", joindate=" + joindate + ", profile_img=" + profile_img + ", common_name=" + common_name + "]";
	}


	
	
}
