package kr.co.neodreams.herit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.AuthCheck;
import kr.co.neodreams.herit.model.MemPoint;
import kr.co.neodreams.herit.model.Member;
import kr.co.neodreams.herit.model.PayInfo;
import kr.co.neodreams.herit.model.Mission;
import kr.co.neodreams.herit.service.MemPointService;
import kr.co.neodreams.herit.service.MemberService;
import kr.co.neodreams.herit.service.MissionService;
import kr.co.neodreams.herit.service.PayInfoService;
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
	@Autowired
	private PayInfoService payService;
	@Autowired
	private MemPointService memService;
	@Autowired
	private MissionService missService;
	
	/**
	 * Member list page 
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView memberList(Member param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal();
		
		List<Member> lst = service.selectMemberList(param);
		log.info("search memberList list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type
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
			log.info("paging data : {}", param);

			if (param.getMenu().equals("1"))		// 결제내역
			{
				PayInfo p = new PayInfo();
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				int cnt = payService.selectPayInfoListCount(p);				
				List<PayInfo> lst = payService.selectPayInfoList(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("list", lst);
			}else if(param.getMenu().equals("2"))	// 포인트 내역
			{
				MemPoint p = new MemPoint();
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				int cnt = memService.selectMemPointListCount(p);				
				List<MemPoint> lst = memService.selectMemPointList(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("list", lst);
			}else if(param.getMenu().equals("3"))	// 미션현황
			{
				Mission p = new Mission();
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				int cnt = missService.selectMissionListCount(p);				
				List<Mission> lst = missService.selectMissionList(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("list", lst);
			}else if(param.getMenu().equals("4"))	// 검진데이터
			{
				
			}
				
			
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
