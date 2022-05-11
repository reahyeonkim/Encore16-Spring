package com.freeflux.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.freeflux.biz.board.BoardVO;
import com.freeflux.biz.board.impl.BoardDAO;


public class GetBoardController implements Controller {

	public GetBoardController() {
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 상세 조회 처리");
		
		//	1. 검색할 게시글 번호 추출
		String seq=request.getParameter("seq");

		//	2. DB 연동 처리
		BoardVO vo= new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAO boardDAO=new BoardDAO();
		BoardVO board=boardDAO.getBoard(vo);
		
//		3. 검색 결과와 화면 정보를 ModelAndView 객체에 저장하여 반환
		ModelAndView mav=new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("getBoard");
		return mav;

	}

}
