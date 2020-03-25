package kr.co.neodreams.herit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.ChkInfo;
import kr.co.neodreams.herit.model.Hospital;
import kr.co.neodreams.herit.service.ChkService;
import kr.co.neodreams.herit.service.HospitalService;
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
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
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
	 * 건강검진 정보 등록 및 수정 폼 출력
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reqregform")
	public ModelAndView reqRegForm(ChkInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if (param.getSeq() > 0)
		{
			ChkInfo info = cService.selectChkInfoById(param);
			log.info("reqRegForm info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new Hospital());
		}
		// 병원 정보 조회
		List<Hospital> codeList = hService.selectHospitalAll();
		mv.addObject("codeList", codeList);
		
		mv.setViewName("admin/chk/chk_reg");
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
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/reqinsert")
	public void reqInsert(HttpServletRequest req, HttpServletResponse res, ChkInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			log.debug("reqinsert param {}", param);			
			retVal = Integer.toString(cService.insertChkInfo(param));
			
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
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/reqmodify")
	public void modifyChkInfo(HttpServletRequest req, HttpServletResponse res, ChkInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
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
