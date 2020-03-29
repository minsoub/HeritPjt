package kr.co.neodreams.herit.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ObjectBuffer;

import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.ChkInfo;
import kr.co.neodreams.herit.model.MemPoint;
import kr.co.neodreams.herit.model.Member;
import kr.co.neodreams.herit.model.PayInfo;
import kr.co.neodreams.herit.model.UserInfo;
import kr.co.neodreams.herit.model.Mission;
import kr.co.neodreams.herit.service.ChkService;
import kr.co.neodreams.herit.service.MemPointService;
import kr.co.neodreams.herit.service.MemberService;
import kr.co.neodreams.herit.service.MissionService;
import kr.co.neodreams.herit.service.PayInfoService;
import kr.co.neodreams.herit.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Member management Controller Class
 * 
 * @author minso
 *
 */
@Slf4j
@Controller
@EnableAutoConfiguration
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
	@Autowired
	private ChkService chkService;
	
	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;
	
	@Resource
	private UserInfo userInfo;
	
	/**
	 * Member list page 
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView memberList(Member param,  HttpServletRequest request, HttpSession session, ModelAndView mv, RedirectAttributes redirectAttributes) throws Exception 
	{		
		//session = request.getSession(true);
		// 관리자 로그인 정보 확인
		Admin loginInfo = (Admin) session.getAttribute("adminInfo");
		
		if (loginInfo != null)
		{
			log.info("admin session info : {}", loginInfo);			
		}else {
			log.info("admin session is null - retury login");
			mv.setViewName("redirect:/admin");
			return mv;
		}
		
		//ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		param.setMem_sts("2");		// 정회원
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal(param);
		
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
	public ModelAndView detailMember(Member param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		Member info = service.selectDetailById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:list");
		}else {
			log.info("search administrator info : {}", info);
			mv.addObject("info", info);	
			
			// 건강검진 등록한 병원 및 검진일자 조회
			ChkInfo chk = new ChkInfo();
			chk.setMem_seq(param.getSeq());   // user seq
			List<ChkInfo> chkList = chkService.selectChkInfoByUserId(chk);
			mv.addObject("chkList", chkList);
			
			if (param.getMenu() == null)
			{
				param.setMenu("1");
			}
			
			log.info("paging data : {}", param);

			if (param.getMenu().equals("1"))		// 결제내역 - 포인트 몰 결제 제외 (포인트몰은 아래 포인트 내역에 다룬다)
			{
				PayInfo p = new PayInfo();
				p.setPay_type("2");            // 1: Point Mall,  2 : Pay plan 
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				p.setSearchFromDt(param.getSearchFromDt());
				p.setSearchToDt(param.getSearchToDt());
				p.setPay_sts(param.getPay_sts());
				int cnt = payService.selectPayListCountByPayPlan(p);				
				List<PayInfo> lst = payService.selectPayListByPayPlan(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("mlist", lst);
			}else if(param.getMenu().equals("2"))	// 포인트 내역
			{
				MemPoint p = new MemPoint();
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				p.setSearchFromDt(param.getSearchFromDt());
				p.setSearchToDt(param.getSearchToDt());
				p.setUse_yn(param.getUse_yn());
				int cnt = memService.selectMemPointByIdListCount(p);				
				List<MemPoint> lst = memService.selectMemPointByIdList(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("plist", lst);
			}else if(param.getMenu().equals("3"))	// 미션현황 - 작업확인 필요
			{
				Mission p = new Mission();
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				p.setSearchFromDt(param.getSearchFromDt());
				p.setSearchToDt(param.getSearchToDt());
				int cnt = missService.selectMissionListCount(p);				
				List<Mission> lst = missService.selectMissionList(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("slist", lst);
			}else if(param.getMenu().equals("4"))	// 검진데이터
			{
				ChkInfo p = new ChkInfo();
				p.setMem_seq(param.getSeq());
				p.setPageNo(param.getPageNo());
				p.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
				p.setSearchFromDt(param.getSearchFromDt());
				p.setSearchToDt(param.getSearchToDt());
				int cnt = chkService.selectChkInfoCount(p);				
				List<ChkInfo> lst = chkService.selectChkInfoList(p);
				
				mv.addObject("totalCnt", cnt);	
				mv.addObject("clist", lst);
			}
				
			mv.addObject("paging", param);
			mv.setViewName("/admin/member/member_detail");
		}
		return mv;
	}
	
	/**
	 * 사용자 정보를 삭제한다. [상태 변경 : 탈퇴회원]
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/delete")
	public void deleteMember(HttpServletRequest req, HttpServletResponse res, Member param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			log.debug("deleteMember param {}", param);			
			retVal = Integer.toString(service.deleteMember(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("deleteMember error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}	

	/**
	 * 임시 비밀번호 생성해서 사용자게 SMS 전송.
	 * SMS 전송 모듈 필요.
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/reqPasswordCreateSendSms")
	public void reqPasswordCreateSendSms(HttpServletRequest req, HttpServletResponse res, Member param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			// 임시 패스워드 생성
			String pass = CommonUtil.getRamdomPassword(10);
			param.setPass(pass);
			
			log.debug("reqPasswordCreateSendSms param {}", param);			
			retVal = Integer.toString(service.updateMemberPassword(param));
			
			/////////////////////////////////////////////////////////////////
			// SMS 전송 ...
			
			
			/////////////////////////////////////////////////////////////////
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("reqPasswordCreateSendSms error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
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
		param.setMem_sts("3");		// 휴먼회원
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal(param);
		
		List<Member> lst = service.selectMemberList(param);
		log.info("search memberFreeList list : {}", lst);
		mv.addObject("totalCnt", cnt);
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
		param.setMem_sts("4");		// 탈퇴회원
		
		int cnt = service.selectMemberListCount(param);
		int total = service.selectMemberTotal(param);
		
		List<Member> lst = service.selectMemberList(param);
		log.info("search memberDelList list : {}", lst);
		mv.addObject("totalCnt", cnt);
		mv.addObject("total", String.valueOf(total));
		
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/member/del_list");
		return mv;		
	}	
	
	/**
	 * 회원 상세 정보>>결제내역에서 결제취소를 수행한다.
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/deletePayCancel")
	public void deletePayCancel(HttpServletRequest req, HttpServletResponse res, PayInfo param) throws Exception {
		
		res.setContentType("text/html;charset=UTF-8");

		param.setPay_type("2");    // 2 : 요금제 결제
		int result = payService.deletePayByIdandSeq(param);
		
		// 이전 결제 내역에 대해서 상태를 업데이트 한다. - 결제 취소키 저장.
		log.debug("last reg key : {}" ,result);
					
		res.getWriter().write(String.valueOf(result));
	}	
	
	/**
	 * 회원 상세 정보>>검진내역에서 상세정보를 조회한다. 
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/chkDetailInfo")
	public void chkDetailInfo(HttpServletRequest req, HttpServletResponse res, ChkInfo param) throws Exception {
		
		res.setContentType("text/html;charset=UTF-8");

		log.debug("param : {}", param);
		ChkInfo chk = chkService.selectChkInfoById(param);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(chk);
		log.debug("Json data : {}", jsonData);						
		res.getWriter().write(jsonData);
	}	
}
