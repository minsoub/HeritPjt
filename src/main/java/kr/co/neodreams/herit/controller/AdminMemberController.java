package kr.co.neodreams.herit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.Admin;
import lombok.extern.slf4j.Slf4j;

/**
 * Member management Controller Class
 * 
 * @author minso
 *
 */
@Slf4j
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	/**
	 * Member list page 
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView memberList(Admin param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		int cnt = service.selectAdminAllCount(param);
		
		List<Admin> lst = service.selectAdminAll(param);
		log.info("search administrator list : {}", lst);
		mv.addObject("total", String.valueOf(cnt));
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/member/member_list\"");
		return mv;		
	}
}
