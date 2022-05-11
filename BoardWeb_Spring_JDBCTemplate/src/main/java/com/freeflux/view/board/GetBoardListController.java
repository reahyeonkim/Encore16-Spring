package com.freeflux.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.freeflux.biz.board.BoardVO;
import com.freeflux.biz.board.impl.BoardDAO;

public class GetBoardListController implements Controller {

	public GetBoardListController() {
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 목록 검색 처리");
		
		//	1. 사용자 입력 정보 추출(검색 기능은 나중에 구현)
		//	2. DB 연동 처리
		BoardVO vo= new BoardVO();
		BoardDAO boardDAO=new BoardDAO();
		
		List<BoardVO> boardList=boardDAO.getBoardList(vo);

		//	3. 검색 결과와 화면 정보를 ModelAndView 객체에 저장하여 반환
		ModelAndView mav=new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("getBoardList");
		return mav;

	}

}
