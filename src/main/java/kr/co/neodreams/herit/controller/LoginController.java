package kr.co.neodreams.herit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	/**
	 * 관리자 로그인 페이지를 호출한다.
	 * 
	 * @return
	 */
	@RequestMapping("/admin")
	public String adminLogin() {
		return "admin/login";
	}
}
