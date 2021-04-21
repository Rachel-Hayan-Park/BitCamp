package com.bc.model.vo;

public class PListVO {
	private int p_no;
	private String sale_id,	purchase_id, picture01, price;
	private String p_title, p_contents, p_status, p_category;
	private String location, p_regdate, picture02;
	private String common_name, selling_date;
	
	public PListVO() {}
	
	public PListVO(int p_no, String sale_id, String purchase_id, String picture01, String price, String p_title,
			String p_contents, String p_status, String p_category, String location, String p_regdate, String picture02,
			String common_name, String selling_date) {
		super();
		this.p_no = p_no;
		this.sale_id = sale_id;
		this.purchase_id = purchase_id;
		this.picture01 = picture01;
		this.price = price;
		this.p_title = p_title;
		this.p_contents = p_contents;
		this.p_status = p_status;
		this.p_category = p_category;
		this.location = location;
		this.p_regdate = p_regdate;
		this.picture02 = picture02;
		this.common_name = common_name;
		this.selling_date = selling_date;
	}
	
	//마이페이지 - 판매내역 확인용 VO
	public PListVO(int p_no, String sale_id, String purchase_id, String picture01, String price, String p_title, String p_status, String common_name, String selling_date) {
		super();
		this.p_no = p_no;
		this.sale_id = sale_id;
		this.purchase_id = purchase_id;
		this.picture01 = picture01;
		this.price = price;
		this.p_title = p_title;
		this.p_status = p_status;
		this.common_name = common_name;
		this.selling_date = selling_date;
	}

	
	public PListVO(int p_no, String purchase_id, String p_status) {
		super();
		this.p_no = p_no;
		this.purchase_id = purchase_id;
		this.p_status = p_status;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getSale_id() {
		return sale_id;
	}

	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}

	public String getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}

	public String getPicture01() {
		return picture01;
	}

	public void setPicture01(String picture01) {
		this.picture01 = picture01;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getP_contents() {
		return p_contents;
	}

	public void setP_contents(String p_contents) {
		this.p_contents = p_contents;
	}

	public String getP_status() {
		return p_status;
	}

	public void setP_status(String p_status) {
		this.p_status = p_status;
	}

	public String getP_category() {
		return p_category;
	}

	public void setP_category(String p_category) {
		this.p_category = p_category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getP_regdate() {
		return p_regdate;
	}

	public void setP_regdate(String p_regdate) {
		this.p_regdate = p_regdate;
	}

	public String getPicture02() {
		return picture02;
	}

	public void setPicture02(String picture02) {
		this.picture02 = picture02;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	@Override
	public String toString() {
		return "PListVO [p_no=" + p_no + ", sale_id=" + sale_id + ", purchase_id=" + purchase_id + ", picture01="
				+ picture01 + ", price=" + price + ", p_title=" + p_title + ", p_contents=" + p_contents + ", p_status="
				+ p_status + ", p_category=" + p_category + ", location=" + location + ", p_regdate=" + p_regdate
				+ ", picture02=" + picture02 + ", common_name=" + common_name + "]";
	}

	public String getSelling_date() {
		return selling_date;
	}

	public void setSelling_date(String selling_date) {
		this.selling_date = selling_date;
	}
	
	
	
	
}
