package kr.co.neodreams.herit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.AuthCheck;
import kr.co.neodreams.herit.model.Member;
import kr.co.neodreams.herit.service.MemberService;
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
	
	@Autowired
	private MemberService service;
	
	/**
	 * Member list page 
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView memberList(Member param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal();
		
		List<Member> lst = service.selectMemberList(param);
		log.info("search memberList list : {}", lst);
		mv.addObject("totalCnt", String.valueOf(cnt));
		mv.addObject("total", String.valueOf(total));
		
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/member/member_list");
		return mv;		
	}
	
	/**
	 * view the detail member data.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	public ModelAndView adminDetail(Member param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		Member info = service.selectDetailById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:list");
		}else {
			log.info("search administrator info : {}", info);
			mv.addObject("info", info);			
			if (param.getMenu() == null)
			{
				param.setMenu("1");
			}
			mv.addObject("paging", param);
			// authority list search
			//List<Member> list = service.selectAuthorityAll(new AuthCheck()); 			
			//mv.addObject("list", list);
			
			
			mv.setViewName("/admin/member/member_detail");
		}
		return mv;
	}
	
	/**
	 * Member free list page 
	 * 
	 * @return
	 */
	@RequestMapping("/freelist")
	public ModelAndView memberFreeList(Member param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal();
		
		List<Member> lst = service.selectMemberList(param);
		log.info("search memberFreeList list : {}", lst);
		mv.addObject("totalCnt", String.valueOf(cnt));
		mv.addObject("total", String.valueOf(total));
		
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/member/free_list");
		return mv;		
	}	
	
	/**
	 * Member delete list page 
	 * 
	 * @return
	 */
	@RequestMapping("/dellist")
	public ModelAndView memberDelList(Member param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal();
		
		List<Member> lst = service.selectMemberList(param);
		log.info("search memberDelList list : {}", lst);
		mv.addObject("totalCnt", String.valueOf(cnt));
		mv.addObject("total", String.valueOf(total));
		
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/member/del_list");
		return mv;		
	}	
}
