package com.bc.model.vo;

public class CommonVO {
	private String common_code, common_name;
	
	public CommonVO() { }

	public CommonVO(String common_code, String common_name) {
		this.common_code = common_code;
		this.common_name = common_name;
	}

	public String getCommon_code() {
		return common_code;
	}

	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}
	
	
}
