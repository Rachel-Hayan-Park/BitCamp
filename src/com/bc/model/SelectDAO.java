package com.bc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bc.model.vo.BoardVO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;
import com.bc.model.vo.ReviewVO;
import com.bc.mybatis.DBService;


public class SelectDAO {
	
	//다슬
	/*메인*/
	public static List<PListVO> getProductList(Map<String, Object> map){
		SqlSession  ss = DBService.getFactory().openSession();
		List<PListVO> list = ss.selectList("main.searchList", map);
		ss.close();
		
		System.out.println(list);
		
		return list;
	}
	
	public static int getProductCount(Map<String, Object> map){
		SqlSession  ss = DBService.getFactory().openSession();
		int totalCount = ss.selectOne("main.searchCount", map);
		ss.close();
		
		System.out.println("test totalCount : " + totalCount);
		
		return totalCount;
	}
	
	//하얀
	/*로그인 부분*/
	//아이디찾기
	public static MemberVO findId(String name, String phonnum) {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("phonnum", phonnum);
		System.out.println("map: " + map);
		
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("member.findId", map);
		System.out.println("mvo: " + mvo);
		ss.close();
		return mvo;		
	}
	//비밀번호찾기
	public static MemberVO findPwd(String id, String email) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		System.out.println("id: " + id + ", email: " + email);
		
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("member.findPwd", map);
		System.out.println("mvo: " + mvo);
		ss.close();
		return mvo;
	}
	
	//로그인
	public static MemberVO login(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		System.out.println("id: " + id + ", email: " + password);
		
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("member.login", map);
		System.out.println("mvo: " + mvo);
		ss.close();
		return mvo;
	}
	
	//회원가입 - ID 중복체크
	public static int joinIdCheck(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.selectOne("member.idCheck", id);
		System.out.println("회원가입 id중복체크 selectdao result: " + result);
		ss.close();
		return result;
	}
	
	//마이페이지 - 지역 확인
	public static MemberVO myPageLocation(String id, String location) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("location", location);
		System.out.println("id: " + id + ", location: " + location);
		
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("mypage.mypageLocation", map);
		ss.close();
		return mvo;
	}	
	
	//프로필정보 수정 후 정보 확인
	public static MemberVO finalProfile(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO fvo = ss.selectOne("mypage.finalProfile", id);
		System.out.println("DAO fvo: " + fvo);
		ss.close();
		return fvo;
	}
	
	//마이페이지 - 구매후기 확인
	public static List<ReviewVO> checkReview(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		List<ReviewVO> list = ss.selectList("mypage.checkReview", id);
		System.out.println("리뷰체크 list: " + list);
		ss.close();
		return list;
	}
	
	//다슬
	//마이페이지 - 구매내역리스트
	public static List<PListVO> getbuyinglist(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		List<PListVO> list = ss.selectList("mypage.buyinglist", id);
		ss.close();
		return list;
	}
	
	//마이페이지 - 구매내역 내 리뷰여부
	public static List<ReviewVO> getReviewIsContain(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		List<ReviewVO> list = ss.selectList("mypage.reviewIscontain", id);
		ss.close();
		
		return list;
	}
	
	//리뷰 - 보내는 상품의 정보 가지고 오기
	public static PListVO getReviewInfoProduct(int p_no) {
		SqlSession ss = DBService.getFactory().openSession();
		PListVO pvo = ss.selectOne("mypage.reviewInfoProduct",p_no);
		ss.close();
		
		return pvo;
	}
	
	//마이페이지 - 판매내역 리스트 조회(판매중인 상품)
	public static List<PListVO> sellingList(String sale_id, String p_status) {
		Map<String, String> map = new HashMap<>();
		map.put("sale_id", sale_id);
		map.put("p_status", p_status);
		System.out.println("sale_id: " + sale_id + ", p_status: " + p_status);
		
		SqlSession ss = DBService.getFactory().openSession();
		List<PListVO> list = ss.selectList("mypage.sellingP_list", map);
		ss.close();
		return list;
	}
	
	//마이페이지 메인 - 이번 달 판매개수 확인
   public static int checkSoldList(String id) {
      SqlSession ss = DBService.getFactory().openSession();
      int count = ss.selectOne("mypage.checkSoldList", id);
      System.out.println("판매갯수: " + count);
      ss.close();
      return count;
   }
	
	//진식
	/*상품한개 조회*/
	public static PListVO selectPrise(int p_no) {
		System.out.println("selectPrise pno : " + p_no);
		System.out.println("dao 실행중");
		SqlSession ss = DBService.getFactory().openSession();
		PListVO pvo = ss.selectOne("PList.selecProduct", p_no);
		System.out.println(pvo);
		ss.close();
		return pvo;
	}
	
	//지상
	/*게시글*/
	// 게시글(BOARD)의 전체 건수 조회
	public static int getTotalCount() {
		SqlSession ss = DBService.getFactory().openSession();
		int count = ss.selectOne("BOARD.totalCount");
		ss.close();
		return count;
	}
	
	
	//페이지에 해당하는 글목록(게시글) 가져오기
	public static List<BoardVO> getList(Map<String, Integer> map) {
		SqlSession ss = DBService.getFactory().openSession();
		List<BoardVO> list = ss.selectList("BOARD.list", map);
		ss.close();
		return list;
	}
	
	//게시글 번호에 해당하는 게시글 하나 조회
	public static BoardVO selectOne(String b_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		BoardVO vo = ss.selectOne("BOARD.one", b_idx);
		ss.close();
		return vo;
	}
}
