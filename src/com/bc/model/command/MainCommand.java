package com.bc.model.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.PListVO;
import com.bc.model.vo.Paging;

public class MainCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//페이징 처리를 위한 paging 객체 생성해서 값을 읽고 설정
				Paging p = new Paging();
				String idx = request.getParameter("idx");
				String str = request.getParameter("str");
				System.out.println("idx " + idx + " str " + str);
				List<PListVO> list = null;
				
				try {
					Map<String, Object> map = new HashMap<>();
					map.put("idx",idx);
					map.put("str",str);
					//1. 전체 게시물의 수를 구하기
					p.setTotalRecord(SelectDAO.getProductCount(map)); 
					p.setTotalPage();
					System.out.println("> try 전체 게시글 수 : "+ p.getTotalRecord());
					System.out.println("> try 전체 페이지 수 : "+ p.getTotalPage());
					request.setAttribute("idx", idx);
					request.setAttribute("str", str);
					
				} catch (NullPointerException e) {
					
				}
				
				//2. 현재 페이지 구하기
				String cPage = request.getParameter("cPage");
				if(cPage != null){
					p.setNowPage(Integer.parseInt(cPage));
				}
				
				System.out.println("cPage = " + cPage);
				
				//3. 현재 페이지에 표시할 게시글 시작번호(begin), 끝번호(end) 구하기
				p.setEnd(p.getNowPage() * p.getNumPerPage()); //현재 페이지 번호 * 페이지당 글수
				p.setBegin(p.getEnd() - p.getNumPerPage() +1);
				
				//(선택적) 3-1 끝 글번호가 데이터건수보다 많은 경우 데이터 건수와 일치
				//(글번호를 paging 객체 내에서 처리함) 
				//예 - 건수 : 10 객체에서 끝 글번호가 13이되면 건수는 10개니까 10개에 맞춰줌
				if(p.getEnd() > p.getTotalRecord()){
					p.setEnd(p.getTotalRecord());
				}
				System.out.println("---------");
				System.out.println("현재 페이지 : " + p.getNowPage());
				System.out.println("시작 글번호 : " + p.getBegin());
				System.out.println("끝 글번호 : " + p.getEnd());
				
				//--------블록 계산하기 ------
				//4. 블로의 시작페이지, 끝페이지 구하기(현재 페이지 번호 사용)
				int nowPage = p.getNowPage();
				int beginPage = (nowPage -1) / p.getPagePerBlock() * p.getPagePerBlock() +1;
				p.setBeginPage(beginPage);
				p.setEndPage(p.getBeginPage() + p.getPagePerBlock() -1);
				
				//4-1. 끝페이지(endPage)가 전체 페이지수(totalPage)보다 크면 
				// 끝페이지 값을 전체페이지수로 변경처리
				if(p.getEndPage() > p.getTotalPage()){
					p.setEndPage(p.getTotalPage());
				}
				System.out.println(">> 시작 페이지(begin) : " + p.getBeginPage());
				System.out.println(">> 끝 페이지(begin) : " + p.getEndPage());
				
				//현재 페이지 기준으로 DB데이터(게시글) 가져오기
				//시작글번호, 끝글번호 저장용 Map 만들기(전달할 파라미터 저장용)
				
				try {
					Map<String, Object> map = new HashMap<>();
					map.put("begin",p.getBegin());
					map.put("end",p.getEnd());
					map.put("str", str);
					map.put("idx", idx);
					System.out.println("try map:" + map);
					//DB에서 현재페이지 표시할 게시글 조회
					list = SelectDAO.getProductList(map);
					
				} catch (NullPointerException e) {
					
				}
				
				System.out.println("> 현재 페이지 글목록(list) : " + list);
				request.setAttribute("list", list);
				request.setAttribute("pvo", p);
				
				return "bitmarket.jsp";
	}
}
