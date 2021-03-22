package com.bc.model.vo;

public class BoardVO {
	private int b_no, hit;
	private String writer_id, b_category, b_title, b_contents;
	private String b_password, b_status, b_regdate, b_answer;
	private String b_answer_date, b_admin_id, img_name;
	
	public BoardVO() {}

	public BoardVO(int b_no, int hit, String writer_id, String b_category, String b_title, String b_contents,
			String b_password, String b_status, String b_regdate, String b_answer, String b_answer_date,
			String b_admin_id, String img_name) {
		super();
		this.b_no = b_no;
		this.hit = hit;
		this.writer_id = writer_id;
		this.b_category = b_category;
		this.b_title = b_title;
		this.b_contents = b_contents;
		this.b_password = b_password;
		this.b_status = b_status;
		this.b_regdate = b_regdate;
		this.b_answer = b_answer;
		this.b_answer_date = b_answer_date;
		this.b_admin_id = b_admin_id;
		this.img_name = img_name;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getB_category() {
		return b_category;
	}

	public void setB_category(String b_category) {
		this.b_category = b_category;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_contents() {
		return b_contents;
	}

	public void setB_contents(String b_contents) {
		this.b_contents = b_contents;
	}

	public String getB_password() {
		return b_password;
	}

	public void setB_password(String b_password) {
		this.b_password = b_password;
	}

	public String getB_status() {
		return b_status;
	}

	public void setB_status(String b_status) {
		this.b_status = b_status;
	}

	public String getB_regdate() {
		return b_regdate;
	}

	public void setB_regdate(String b_regdate) {
		this.b_regdate = b_regdate;
	}

	public String getB_answer() {
		return b_answer;
	}

	public void setB_answer(String b_answer) {
		this.b_answer = b_answer;
	}

	public String getB_answer_date() {
		return b_answer_date;
	}

	public void setB_answer_date(String b_answer_date) {
		this.b_answer_date = b_answer_date;
	}

	public String getB_admin_id() {
		return b_admin_id;
	}

	public void setB_admin_id(String b_admin_id) {
		this.b_admin_id = b_admin_id;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	@Override
	public String toString() {
		return "BoardVO [b_no=" + b_no + ", hit=" + hit + ", writer_id=" + writer_id + ", b_category=" + b_category
				+ ", b_title=" + b_title + ", b_contents=" + b_contents + ", b_password=" + b_password + ", b_status="
				+ b_status + ", b_regdate=" + b_regdate + ", b_answer=" + b_answer + ", b_answer_date=" + b_answer_date
				+ ", b_admin_id=" + b_admin_id + ", img_name=" + img_name + "]";
	}
	
}
