package com.bc.model.vo;

public class BoardVO {
	private String n_title, n_contents;

	public BoardVO() {
		super();
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_contents() {
		return n_contents;
	}

	public void setN_contents(String n_contents) {
		this.n_contents = n_contents;
	}

	@Override
	public String toString() {
		return "BoardVO [n_title=" + n_title + ", n_contents=" + n_contents + "]";
	}

	
	
	
	
}
