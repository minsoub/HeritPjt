package kr.co.neodreams.herit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.ChkInfo;
import kr.co.neodreams.herit.model.Hospital;
import kr.co.neodreams.herit.model.Member;
import kr.co.neodreams.herit.model.PayInfo;
import kr.co.neodreams.herit.service.ChkService;
import kr.co.neodreams.herit.service.HospitalService;
import kr.co.neodreams.herit.service.MemberService;
import kr.co.neodreams.herit.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Check data controller class
 * 
 * @author minso
 *
 */
@Slf4j
@Controller
@RequestMapping("/admin/chk")
public class AdminChkController {
	@Autowired
	private HospitalService hService;
	
	@Autowired
	private MemberService  mService;
	
	@Autowired
	private ChkService cService;
	
	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;
	
	/**
	 * 건강검진 요청 list page 
	 * 
	 * @return
	 */
	@RequestMapping("/reqlist")
	public ModelAndView reqList(ChkInfo param, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		log.debug("session id : " + session.getAttribute("id"));
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = cService.selectChkInfoCount(param);
		List<ChkInfo> lst = cService.selectChkInfoList(param);
		log.info("search reqlist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/chk/req_list");
		return mv;		
	}
	
	/**
	 * 건강검진 정보 신규 등록 폼 출력
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reqnewform")
	public ModelAndView reqNewRegForm(ChkInfo param, HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String reg_id = (String) session.getAttribute("id");
		log.debug("admin id : " + reg_id);
		
		if (param.getSeq() > 0)
		{
			ChkInfo info = cService.selectChkInfoById(param);
			log.info("reqRegForm info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new ChkInfo());
		}
		// 병원 정보 조회
		List<Hospital> codeList = hService.selectHospitalAll();
		mv.addObject("codeList", codeList);
		
		mv.setViewName("admin/chk/req_newreg");
		return mv;
	}
	
	/**
	 * 건강검진 정보 등록 및 수정 폼 출력
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reqregform")
	public ModelAndView reqRegForm(ChkInfo param, HttpServletRequest request,  HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String reg_id = (String) session.getAttribute("id");
		log.debug("admin id : " + reg_id);
		
		if (param.getSeq() > 0)
		{
			ChkInfo info = cService.selectChkInfoById(param);
			log.info("reqRegForm info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new ChkInfo());
		}
		// 병원 정보 조회
		List<Hospital> codeList = hService.selectHospitalAll();
		mv.addObject("codeList", codeList);
		
		mv.setViewName("admin/chk/req_reg");
		return mv;
	}	
	
	/**
	 * 건강검진 요청 수기 등록
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reqdetail")
	public ModelAndView reqlDetail(ChkInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		ChkInfo info = cService.selectChkInfoById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:reqlist");
		}else {
			log.info("search reqlDetail info : {}", info);
			mv.addObject("info", info);
			mv.addObject("paging", param);
			mv.setViewName("/admin/chk/req_detail");
		}
		return mv;
	}	
	
	/**
	 * 건강검진 정보를 등록한다.
	 * 관리자가 등록할 때 사용한다. 
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping("/reqinsert")
	public void reqInsert(HttpSession session, HttpServletRequest req, HttpServletResponse res, ChkInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			
			param.setSts("Y"); 		// 작성완료 상태
			session = req.getSession(true);
			log.debug("reqinsert param {}", param);			
			retVal = Integer.toString(cService.insertChkInfo(param));
			String reg_id = (String) session.getAttribute("id");
			log.debug("admin id : " + reg_id);
			param.setReg_id(reg_id);
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("reqinsert error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}
	
	/**
	 * 건강검진 정보를 수정한다.
	 * 고객 요청 이후 관리자가 정보 등록 시 호출된다. 
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping("/reqmodify")
	public void modifyChkInfo(HttpServletRequest req, HttpServletResponse res, HttpSession session, ChkInfo param) throws Exception {
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			// 관리자 아이디 등록
			String reg_id = (String) session.getAttribute("id");
			log.debug("admin id : " + reg_id);
			param.setReg_id(reg_id);
			
			log.debug("modifyChkInfo param {}", param);			
			retVal = Integer.toString(cService.updateChkInfo(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("modifyChkInfo error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}	
	
	
	/**
	 * 건강검진 정보를 삭제한다.
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/reqdelete")
	public void deleteReq(HttpServletRequest req, HttpServletResponse res, ChkInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			log.debug("reqdelete param {}", param);			
			retVal = Integer.toString(cService.deleteChkInfo(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("reqdelete error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}	
	
	
	/**
	 * 개별 결제내역에 대한 리스트를 출력한다. 
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/reqSearchlistByMemberName")	
	public void reqSearchlistByMemberName(HttpServletRequest req, HttpServletResponse res, Member param) throws Exception {
		res.setContentType("text/html;charset=UTF-8");

		List<Map<String, Object>> list = mService.selectMemberSearchList(param);
		
		// JSON 데이터 생성
		String jsonData = CommonUtil.getJsonStringFromList(list);
		
		log.debug("Json data : {}", jsonData);
		res.getWriter().write(jsonData);
	}	
	
	/**
	 * 건강검진 현황 list page 
	 * 
	 * @return
	 */
	@RequestMapping("/chklist")
	public ModelAndView chkList(ChkInfo param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = cService.selectChkInfoCount(param);
		List<ChkInfo> lst = cService.selectChkInfoList(param);
		log.info("search chklist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/chk/chk_list");
		return mv;		
	}	
	
	
	/**
	 * Hospital list page 
	 * 
	 * @return
	 */
	@RequestMapping("/hlist")
	public ModelAndView hospitalList(Hospital param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = hService.selectHospitalListCount(param);
		List<Hospital> lst = hService.selectHospitalList(param);
		log.info("search hospitalList list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/chk/hospital_list");
		return mv;		
	}
	
	/**
	 * view the detail Hospital data.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/hdetail")
	public ModelAndView hospitalDetail(Hospital param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		Hospital info = hService.selectHospitalById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:list");
		}else {
			log.info("search hospitalDetail info : {}", info);
			mv.addObject("info", info);
			mv.addObject("paging", param);
			mv.setViewName("/admin/chk/hospital_detail");
		}
		return mv;
	}
	
	/**
	 * Hospital Register/Update Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/hregform")
	public ModelAndView hospitalRegForm(Hospital param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if (param.getSeq() > 0)
		{
			Hospital info = hService.selectHospitalById(param);
			log.info("hospitalModifyForm info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new Hospital());
		}
		mv.setViewName("admin/chk/hospital_reg");
		return mv;
	}

	
	/**
	 * register new Hospital
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/hinsert")
	public void hospitalInsert(HttpServletRequest req, HttpServletResponse res, Hospital param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("register param {}", param);			
			retVal = Integer.toString(hService.insertHospital(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("insert error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}
	
	/**
	 * update the Hospital data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/hmodify")
	public void modifyHospital(HttpServletRequest req, HttpServletResponse res, Hospital param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("modify param {}", param);			
			retVal = Integer.toString(hService.updateHospital(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("modify error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}
	
	/**
	 * delete the Hospital data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/hdelete")
	public void deleteHospital(HttpServletRequest req, HttpServletResponse res, Hospital param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("delete param {}", param);			
			retVal = Integer.toString(hService.deleteHospital(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("delete error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}
}
