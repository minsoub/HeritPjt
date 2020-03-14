package kr.co.neodreams.herit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String memberList() {
		return "admin/member/member_list";
	}
}
