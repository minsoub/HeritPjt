package kr.co.neodreams.herit.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.Comcode;
import kr.co.neodreams.herit.model.MemPoint;
import kr.co.neodreams.herit.model.PayInfo;
import kr.co.neodreams.herit.model.PayPlanInfo;
import kr.co.neodreams.herit.model.Product;
import kr.co.neodreams.herit.service.CodeService;
import kr.co.neodreams.herit.service.PayInfoService;
import kr.co.neodreams.herit.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/pay")
public class AdminPayController {
	@Value("${file.uploadpath}")
	private String uploadpath;
	
	@Autowired
	private PayInfoService  pService;		// Pay history Service
	@Autowired
	private CodeService     cService;       // Common code service
	
	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;
	
	/**
	 * 결제관리 > 결제 내역 리스트 페이지 출력
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/paylist")
	public ModelAndView paylist(PayPlanInfo param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if (param.getSearchDt() == null || param.getSearchDt().equals(""))
		{			
			param.setSearchDt(CommonUtil.getCurrentMonth());    // 당월로 설정한다.
		}
		if (param.getMode() != null && !param.getMode().equals(""))
		{
			// 날짜 조절
			param.setSearchDt(CommonUtil.getSearchMonth(param.getSearchDt(), param.getMode()));
		}
		log.info("paramter : {}", param);
		

		List<Map> lst = pService.selectPayStsList(param);
		log.info("search paylist list : {}", lst);

		Map sum = pService.selectPayStsSum(param);
		
		mv.addObject("sum", sum);
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/pay/pay_hist");
		return mv;		
	}
	
	/**
	 * 결제 현황에서 개인별 상세현황 리스트를 출력한다.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping("/paydetail")
	public ModelAndView paydetail(PayInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		param.setPay_type("2");     // 1: 포인트몰, 2:요금제 결제
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = pService.selectPayListCountByPayPlan(param);
		List<PayInfo> lst = pService.selectPayListByPayPlan(param);
		log.info("search paydetail list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/pay/pay_list");
		return mv;
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
	@RequestMapping("/paylistByMemberId")	
	public void paylistByMemberId(HttpServletRequest req, HttpServletResponse res, PayInfo param) throws Exception {
		res.setContentType("text/html;charset=UTF-8");

		param.setPay_type("2");    // 2 : 요금제 결제
		List<Map<String, Object>> list = pService.selectPayListById(param);
		
		// JSON 데이터 생성
		String jsonData = CommonUtil.getJsonStringFromList(list);
		
		log.debug("Json data : {}", jsonData);
		res.getWriter().write(jsonData);
	}
	
	/**
	 * 개별 결제내역을 삭제한다. 
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deletePayByIdandSeq")	
	public void deletePayByIdandSeq(HttpServletRequest req, HttpServletResponse res, PayInfo param) throws Exception {
		res.setContentType("text/html;charset=UTF-8");

		param.setPay_type("2");    // 2 : 요금제 결제
		int result = pService.deletePayByIdandSeq(param);
					
		res.getWriter().write(String.valueOf(result));
	}	
	
	
	/**
	 * Pay Plan information list page
	 * 
	 * @return
	 */
	@RequestMapping("/payplanlist")
	public ModelAndView payplanlist(PayPlanInfo param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = pService.selectPayInfoListCount(param);
		List<PayPlanInfo> lst = pService.selectPayPlanList(param);
		log.info("search payplanlist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/pay/pay_plan_list");
		return mv;		
	}
	
	
	/**
	 * Pay Plan Register/Update Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/payplanregform")
	public ModelAndView payplanRegForm(PayPlanInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if (param.getSeq() > 0)
		{
			PayPlanInfo info = pService.selectPayPlanById(param);
			log.info("productRegForm info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new PayPlanInfo());
		}
		// category search
		List<Comcode> codeList = cService.selectCodeByGroup("P");
		mv.addObject("codelist", codeList);
		mv.setViewName("admin/pay/pay_plan_reg");
		return mv;
	}
	
	/**
	 * Pay Plan Detail Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/payplandetail")
	public ModelAndView payplanDetail(PayPlanInfo param, PayInfo param2, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("params : {}", param);
		PayPlanInfo info = pService.selectPayPlanById(param);
		log.info("payplanDetail info : {}", info);
		mv.addObject("info", info);

		// 구매자 명단 조회				
		param2.setPay_type("2");    // 1:포인트 몰, 2 : 요금제 결제
		param2.setP_key(param.getSeq());
		param2.setPageStartNo((param2.getPageNo()-1) * param2.getPerPageCnt());
		log.info("params2 : {}", param2);
		int cnt = pService.selectPayListCountByPayPlan(param2);
		List<PayInfo> lst = pService.selectPayListByPayPlan(param2);
		log.info("search payplandetail list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param2);
		
		if (param2.getMode() != null && param2.getMode().equals("Y"))
		{
			mv.addObject("mode", param2.getMode());
		}else {
			mv.addObject("mode", "N");
		}
		
		mv.setViewName("admin/pay/pay_plan_detail");
		return mv;
	}	
	
	/***
	 * Insert/Update the Pay Plan data (include image file)
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/payplansave")
	public ModelAndView productSave(PayPlanInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result;
		log.debug("file name : {}", param.getFiles().getOriginalFilename());
		// 파일 업로드 처리
		if (!param.getFiles().getOriginalFilename().isEmpty())
		{
			log.debug("file upload process {}", param);
			String sourceFileName = param.getFiles().getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();	// 확자자		
			String destinationFileName  = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			File destinationFile = new File(uploadpath+File.separator +destinationFileName);            
			param.getFiles().transferTo(destinationFile);
			
			param.setFile_name(destinationFileName);
		}
		        
		// 신규/수정 처리
		if (param.getSeq() == 0)	// insert
		{
			param.setUse_yn("Y");		// use check
			result = pService.insertPayPlan(param);
		}else {
			result = pService.updatePayPlan(param);
		}
		
		mv.setViewName("redirect:payplanlist");
		return mv;
	}
	
	/**
	 * delete the Pay Plan data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/payplandelete")
	public void payplanDelete(HttpServletRequest req, HttpServletResponse res, PayPlanInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("payplandelete param {}", param);			
			retVal = Integer.toString(pService.deletePayPlan(param));
			
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
