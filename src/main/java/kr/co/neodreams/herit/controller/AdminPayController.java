package kr.co.neodreams.herit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.MemPoint;
import kr.co.neodreams.herit.model.PayPlanInfo;
import kr.co.neodreams.herit.service.PayInfoService;
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
	DataSourceTransactionManager dataSourceTransactionManager;
	
	/**
	 * Pay Plan information list page
	 * 
	 * @return
	 */
	@RequestMapping("/payplanlist")
	public ModelAndView pointlist(PayPlanInfo param, HttpServletRequest request) throws Exception {
		
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
}
