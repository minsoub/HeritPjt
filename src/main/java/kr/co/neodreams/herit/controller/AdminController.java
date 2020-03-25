package kr.co.neodreams.herit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		log.debug("administrator login page called...");
		return "admin/login";
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
	public ModelAndView loginProcess(Admin param, HttpServletRequest request, HttpSession session) throws Exception
	{	
		session = request.getSession(true);
		
		Admin data = service.selectAdminLoginById(param);
		log.info("/loginProcess [data] : {}", data);
		ModelAndView mv = new ModelAndView();
		
		if (data != null)
		{
			// 세션 등록
			session.setAttribute("auth_chk", "0");
			session.setAttribute("id", data.getId());
			// 메뉴 권한에 대해서 처리해야 한다.
			session.setAttribute("adminCheck", true);
			
			log.debug("session id : {}", session.getAttribute("id"));
			// 관리자 last_login_dt 업데이트
			int result = service.updateAdminLoginDt(data);
			
			mv.setViewName("redirect:/admin/member/list");
		}else {
			mv.addObject("returnCode", "9999");
			mv.setViewName("admin/login");
		}
		
		return mv;
	}
	
	/**
	 * administrator logout
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public ModelAndView logoutProcess(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		session.invalidate();
		log.info("session invalidate called...");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("returnCode", "");
		mv.setViewName("admin/login");
		
		return mv;
	}
}
