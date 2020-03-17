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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.AuthCheck;
import kr.co.neodreams.herit.model.Faq;
import kr.co.neodreams.herit.model.FaqCategory;
import kr.co.neodreams.herit.service.AdminAuthorityService;
import lombok.extern.slf4j.Slf4j;

/**
 * Administrator Management Controller class
 * 
 * @author minso
 *
 */
@Slf4j
@Controller
@RequestMapping("/admin/authority")
public class AdminAuthController {

	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;
	
	@Autowired
	AdminAuthorityService service;
	
	/**
	 * Administrator list
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public ModelAndView adminList(Admin param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		int cnt = service.selectAdminAllCount(param);
		
		List<Admin> lst = service.selectAdminAll(param);
		log.info("search administrator list : {}", lst);
		mv.addObject("total", String.valueOf(cnt));
		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("/admin/mng/admin_list");
		return mv;
	}
	
	/**
	 * view the detail administrator data.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	public ModelAndView adminDetail(Admin param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		Admin info = service.selectAdminById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:list");
		}else {
			log.info("search administrator info : {}", info);
			mv.addObject("info", info);
			mv.addObject("paging", param);
			// authority list search
			List<AuthCheck> list = service.selectAuthorityAll(new AuthCheck()); 			
			mv.addObject("list", list);
			mv.setViewName("/admin/mng/admin_detail");
		}
		return mv;
	}
	
	/**
	 * Administrator Register Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regform")
	public ModelAndView regForm(Admin param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// authority list search
		List<AuthCheck> list = service.selectAuthorityAll(new AuthCheck()); 
		
		mv.addObject("list", list);
		mv.setViewName("/admin/mng/admin_reg");
		return mv;
	}
	/**
	 * check the existed id
	 * 
	 * @param req
	 * @param res
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/idcheck/{id}")
	public void idcheck(HttpServletRequest req, HttpServletResponse res, @PathVariable String id) throws Exception {
		String retVal = "-1";
		Admin param = new Admin();
		param.setId(id);;
		Admin info = service.selectAdminById(param);
		
		if (info == null)
		{
			retVal =  "0";
		}else {
			retVal = "1";
		}
		
		res.getWriter().write(retVal);
	}
	
	
	/**
	 * register new administrator
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/insert")
	public void insert(HttpServletRequest req, HttpServletResponse res, Admin param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("register param {}", param);			
			retVal = Integer.toString(service.insertAdmin(param));
			
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
	 * update the administrator data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/modify")
	public void modifyAdmin(HttpServletRequest req, HttpServletResponse res, Admin param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("modify param {}", param);			
			retVal = Integer.toString(service.updateAdmin(param));
			
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
	 * delete the administrator data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/delete")
	public void deleteAdmin(HttpServletRequest req, HttpServletResponse res, Admin param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("delete param {}", param);			
			retVal = Integer.toString(service.deleteAdmin(param));
			
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
	
	/**
	 * Administrator Authority list
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/authlist")
	public ModelAndView adminAuthList(AuthCheck param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		List<AuthCheck> lst = service.selectAuthorityAll(param);
		log.info("search Authority list : {}", lst);

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("/admin/mng/auth_list");
		return mv;
	}
	
	/**
	 * view the detail administrator authority data.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/authdetail")
	public ModelAndView authDetail(AuthCheck param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		AuthCheck info = service.selectAuthorityById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:auth_list");
		}else {
			log.info("search authdetail info : {}", info);
			mv.addObject("info", info);
			mv.addObject("paging", param);
			mv.addObject("mode", "U");		// modify mode
			mv.addObject("seq",   info.getSeq());
			mv.setViewName("/admin/mng/auth_reg");
		}
		return mv;
	}
	
	/**
	 * Administrator Authority Register Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/authRegform")
	public ModelAndView authRegform(AuthCheck param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mode", "I");		// insert mode
		mv.addObject("seq",  "0");		// insert mode (seq = 0)
		mv.addObject("info", new AuthCheck());
		mv.setViewName("/admin/mng/auth_reg");
		return mv;
	}
	
	/**
	 * register/update/delete the administrator authority data.
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/authProccess")
	public void authProccess(HttpServletRequest req, HttpServletResponse res, AuthCheck param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("authProccess param {}", param);
			if (param.getMode().equals("I"))
				retVal = Integer.toString(service.insertAuthority(param));
			else if(param.getMode().equals("U"))
				retVal = Integer.toString(service.updateAuthority(param));
			else if(param.getMode().equals("D"))
				retVal = Integer.toString(service.deleteAuthority(param));
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("authProccess error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}
	
	/**
	 * FAQ list
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/faqlist")
	public ModelAndView adminFaqList(Faq param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		// faq list search
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int totalCnt = service.selectFaqAllCount(param);		
		List<Faq> lst = service.selectFaqAll(param);
		log.info("search faqlist list : {}", lst);
		
		// category code search
		List<FaqCategory> categoryList = service.selectFaqCategory();

		mv.addObject("totalCnt", totalCnt);
		mv.addObject("list", lst);
		mv.addObject("categoryList", categoryList);
		mv.addObject("paging", param);
		mv.addObject("category", param.getCategory());
		mv.addObject("searchMode", param.getSearchMode() == null ? "ALL" :param.getSearchMode());		// 전체, 미답변, 답변완료 검색 조건
		mv.setViewName("/admin/mng/faq_list");
		return mv;
	}	
	
	/**
	 * insert/delete/modify the faq category data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/faqCategoryProcess")
	public void faqCategoryProcess(HttpServletRequest req, HttpServletResponse res, FaqCategory param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			log.debug("delete param {}", param);	
			if (param.getMode().equals("I")) {
				retVal = Integer.toString(service.insertFaqCategory(param));
			
				int seq = service.selectFaqCategorySeq();
				retVal = String.valueOf(seq);		// max seq number
			}
			else if(param.getMode().equals("U"))
				retVal = Integer.toString(service.updateFaqCategory(param));
			else if(param.getMode().equals("D"))
				retVal = Integer.toString(service.deleteFaqCategory(param));
			
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
	
	/**
	 * view the detail administrator authority data.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/faqdetail")
	public ModelAndView faqDetail(Faq param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		Faq info = service.selectFaqById(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:faq_list");
		}else {
			log.info("search faqDetail info : {}", info);
			if (info.getRe_content() != null)
				info.setRe_content(info.getRe_content().replace("\\r\\n", "\n"));
			mv.addObject("info", info);
			mv.addObject("paging", param);
			mv.addObject("mode", "U");				// modify mode
			mv.addObject("seq",   info.getSeq());
			mv.setViewName("/admin/mng/faq_reg");
		}
		return mv;
	}	
	
	/**
	 * register/update/delete the administrator authority data.
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/faqProcess")
	public void faqProcess(HttpServletRequest req, HttpServletResponse res, Faq param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("authProccess param {}", param);
			if (param.getMode().equals("I"))
			{
				param.setRe_reg_id(req.getSession().getAttribute("id").toString());		// 관리자 세션
				retVal = Integer.toString(service.insertFaq(param));
			}
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("authProccess error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}	
}
