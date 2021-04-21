package com.bc.model.vo;

public class ReviewVO {
	private int p_no;
	private String r_content, r_regdate, purchase_id, r_status;
	
	public ReviewVO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewVO(int p_no, String r_content,  String purchase_id) {
		super();
		this.p_no = p_no;
		this.r_content = r_content;
		this.purchase_id = purchase_id;
	}
	
	public ReviewVO(int p_no, String purchase_id, String r_status, String r_content, String r_regdate) {
		super();
		this.p_no = p_no;
		this.purchase_id = purchase_id;
		this.r_status = r_status;
		this.r_content = r_content;
		this.r_regdate = r_regdate;
	}
	

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public String getR_regdate() {
		return r_regdate;
	}

	public void setR_regdate(String r_regdate) {
		this.r_regdate = r_regdate;
	}

	public String getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}

	
	public String getR_status() {
		return r_status;
	}

	public void setR_status(String r_status) {
		this.r_status = r_status;
	}

	@Override
	public String toString() {
		return "ReviewVO [p_no=" + p_no + ", r_content=" + r_content + ", r_regdate=" + r_regdate + ", purchase_id="
				+ purchase_id + ", r_status=" + r_status + "]";
	}

	
	
	
	
}
