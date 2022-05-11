/**
 * 
 */
package com.freeflux.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.freeflux.biz.user.UserVO;
import com.freeflux.biz.user.impl.UserDAO;


/**
 * @author tuesvonita
 *
 */
public class LoginController implements Controller {

	/**
	 * 
	 */
	public LoginController() {
	}

	/* (non-Javadoc)
	 * @see com.freeflux.view.controller.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		
		//	1. 사용자 입력 정보 추출
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		//	2. DB 연동 처리
		UserVO vo=new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO=new UserDAO();
		UserVO user=userDAO.getUser(vo);
		
		//	3. 화면 내비게이션
		ModelAndView mav=new ModelAndView();
		if(user != null){
			mav.setViewName("redirect:getBoardList.do");
		}else{
			mav.setViewName("redirect:login.jsp");
		}
		return mav;
	}

}
