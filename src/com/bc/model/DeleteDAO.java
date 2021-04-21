package com.bc.model;

import org.apache.ibatis.session.SqlSession;

import com.bc.mybatis.DBService;

public class DeleteDAO {
	
	//마이페이지 - 판매내역 - 상품삭제
	public static int deletePlist(String p_no) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.delete("mypage.deleteP_list", p_no);
		ss.close();
		return result;
	}
	
	//마이페이지 - 회원탈퇴
	public static int deleteMember(String id) { 
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.delete("mypage.deleteMember", id);
		ss.close();
		return result;
	}
}
