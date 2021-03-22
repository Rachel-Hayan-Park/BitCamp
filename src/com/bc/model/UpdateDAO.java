package com.bc.model;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;
import com.bc.model.vo.ReviewVO;
import com.bc.mybatis.DBService;

public class UpdateDAO {
	//마이페이지 - 프로필수정
	public static int updateProfile(MemberVO mvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("mypage.updateProfile", mvo);
		System.out.println("DAO result: " + result);
		ss.close();
		return result;
	}
	
	//마이페이지 - 판매내역 상태 수정(판매중->판매완료로 값 수정)
	public static int changeStatusToSold(PListVO pvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		//System.out.println("pvo.getP_status(): " + pvo.getP_status());
		//System.out.println("pvo.getprice: " + pvo.getPrice());
		int result = ss.update("mypage.changeStatusToSold", pvo);
		System.out.println("업데이트 판매완료 수정 result: " + result);
		ss.close();
		return result;
	}
	
	//마이페이지- 판매내역 상태 수정(판매완료->판매중으로 값 수정)
	public static int changeStatusToSelling(PListVO pvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		//System.out.println("pvo.getP_status(): " + pvo.getP_status());
		//System.out.println("pvo.getprice: " + pvo.getPrice());
		int result = ss.update("mypage.changeStatusToSelling", pvo);
		System.out.println("업데이트 판매중 수정 result: " + result);
		ss.close();
		return result;
	}
	
	//상품 상세리스트 - 상품 구매하기 버튼 클릭 시
	public static int updateBuying(PListVO pvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("PList.updateBuying", pvo);
		System.out.println("상품 구매버튼 클릭 시 결과: " + result);
		ss.close();
		return result;
	}
	
	//리뷰내용
	//다슬
	/*리뷰작성*/
	public static int updateReview(Map<String, Object> map) {
		SqlSession ss = DBService.getFactory().openSession(true);
		 int result = ss.update("mypage.reviewSend", map);
		 
		 System.out.println("리뷰작성 결과 result : " + result);
		 ss.close();
		 return result;
	}
	
	//진식
	public static int updatePrise(PListVO pvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("mypage.updatePrise", pvo);
		System.out.println("DAO result: " + result);
		ss.close();
		return result;
	}
}
