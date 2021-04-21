package com.bc.model;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bc.model.vo.BoardVO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;
import com.bc.model.vo.ReviewVO;
import com.bc.mybatis.DBService;

public class CreateDAO {
	/*회원가입 추가*/
	public static int insert(MemberVO mvo) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.insert("member.joinInsert", mvo);
		System.out.println(result);
		if(result < 1) {
			System.out.println("실패");
		}else {
			ss.commit();
			System.out.println("성공");
		}
		ss.close();
		return result;
	}
	
	/*게시글*/
	//게시글 입력 처리
	public static int insert(BoardVO bvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		 int result = ss.insert("Board.insert", bvo);
		 ss.close();
		 return result;
	}
	
	/*상품등록*/
	public static int insert(PListVO pvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		 int result = ss.insert("PList.insert", pvo);
		 
		 System.out.println("상품등록 result : " + result);
		 ss.close();
		 return result;
	}
	
	//하얀
	/*리뷰테이트블 추가*/
	public static int insertReview(ReviewVO rvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("mypage.insertReview", rvo);
		System.out.println("구매자아이디: " + rvo.getPurchase_id());
		ss.close();
		return result;	
	}
	
}
