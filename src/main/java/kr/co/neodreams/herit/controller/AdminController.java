package kr.co.neodreams.herit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private LoginService service;
	
	/**
	 * 관리자 로그인 페이지를 호출한다.
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String adminLogin() {
		return "/admin/login";
	}
	
	/**
	 * 관리자 로그인 처리
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginProcess")
	public ModelAndView loginProcess(@RequestBody Admin param, HttpServletRequest request) throws Exception
	{	
		HttpSession session = request.getSession();
		
		Admin data = service.selectAdminLoginById(param.getId(), param.getPass());
		ModelAndView mv = new ModelAndView();
		
		if (data != null)
		{
			// 세션 등록
			session.setAttribute("auth_chk", "0");
			mv.setViewName("/admin/member/member_list");
		}else {
			mv.addObject("error", "관리자 정보가 없습니다!!!");
			mv.setViewName("/admin/login");
		}
		
		return mv;
	}
}
