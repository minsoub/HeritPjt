package kr.co.neodreams.herit.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.service.MemPointService;
import kr.co.neodreams.herit.service.ProductService;
import kr.co.neodreams.herit.config.PropertyConfiguration;
import kr.co.neodreams.herit.model.Hospital;
import kr.co.neodreams.herit.model.MemPoint;
import kr.co.neodreams.herit.model.Product;
import lombok.extern.slf4j.Slf4j;

/**
 * Point data controller class
 * 
 * @author minso
 *
 */
@Slf4j
@Controller
@RequestMapping("/admin/point")
@EnableConfigurationProperties(PropertyConfiguration.class)
public class AdminMngPointController {
	@Autowired
    private PropertyConfiguration appConfig;
	
	@Autowired
	private MemPointService service;		// Point service
	@Autowired
	private ProductService  pService;		// Product service

	
	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;
	
	/**
	 * Point list page (job need)
	 * 
	 * @return
	 */
	@RequestMapping("/pointlist")
	public ModelAndView pointlist(MemPoint param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemPointListCount(param);
		List<MemPoint> lst = service.selectMemPointList(param);
		log.info("search pointlist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/point/point_list");
		return mv;		
	}
	
	
	/**
	 * Private Point list page 
	 * 
	 * @return
	 */
	@RequestMapping("/privatelist")
	public ModelAndView privatelist(MemPoint param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemPointListCount(param);
		List<MemPoint> lst = service.selectMemPointList(param);
		log.info("search privatelist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/point/private_point_list");
		return mv;		
	}
	
	/**
	 * view the detail Private Point data.
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/privatedetail")
	public ModelAndView privatedetail(MemPoint param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);

		// 개인 포인트 내역
		MemPoint info = service.selectMemPointById(param);
		
		// 개인 포인트 현황 리스트
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemPointByIdListCount(param);
		List<MemPoint> list = service.selectMemPointByIdList(param);
		
		if (info == null)
		{
			mv.setViewName("redirect:privatelist");
		}else {
			log.info("search privatedetail info : {}", info);
			mv.addObject("info", info);
			mv.addObject("totalCnt", cnt);		// need to Integer type
			mv.addObject("list", list);
			mv.addObject("paging", param);
			mv.setViewName("/admin/point/private_point_detail");
		}
		return mv;
	}
	
	
	/**
	 * Product list page (job need)
	 * 
	 * @return
	 */
	@RequestMapping("/productlist")
	public ModelAndView productlist(MemPoint param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemPointListCount(param);
		List<MemPoint> lst = service.selectMemPointList(param);
		log.info("search productlist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.addObject("imgPath", appConfig.getUploadpath()+File.pathSeparator);		// image directory
		mv.setViewName("admin/point/product_list");
		return mv;		
	}
	
	/**
	 * Product Register/Update Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productregform")
	public ModelAndView productRegForm(Product param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if (param.getSeq() > 0)
		{
			Product info = pService.selectProductById(param);
			log.info("productRegForm info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new Product());
		}
		mv.setViewName("admin/point/product_reg");
		return mv;
	}
	
	/**
	 * Product Detail Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productdetail")
	public ModelAndView productDetail(Product param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		Product info = pService.selectProductById(param);
		log.info("productDetail info : {}", info);
		mv.addObject("info", info);

		mv.setViewName("admin/point/product_detail");
		return mv;
	}	
	/***
	 * Insert/Update the Product data (include image file)
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productsave")
	public ModelAndView productSave(Product param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// 파일 업로드 처리
		if (param.getFiles() != null)
		{
			String sourceFileName = param.getFiles().getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();	// 확자자		
			String destinationFileName  = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			File destinationFile = new File(appConfig.getUploadpath()+File.pathSeparator+destinationFileName);            
			param.getFiles().transferTo(destinationFile);
			
			param.setProduct_img(destinationFileName);
		}
		        
		// 신규/수정 처리
		if (param.getSeq() == 0)	// insert
		{
			int result = pService.insertProduct(param);
		}else {
			int result = pService.updateProduct(param);
		}
		
		mv.setViewName("redirect:productlist");
		return mv;
	}	
	
	
	/**
	 * Point Mall list page (job need)
	 * 
	 * @return
	 */
	@RequestMapping("/malllist")
	public ModelAndView malllist(MemPoint param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemPointListCount(param);
		List<MemPoint> lst = service.selectMemPointList(param);
		log.info("search productlist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/point/mall_list");
		return mv;		
	}	
	
	/**
	 * Buy History list page (job need)
	 * 
	 * @return
	 */
	@RequestMapping("/buylist")
	public ModelAndView buylist(MemPoint param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = service.selectMemPointListCount(param);
		List<MemPoint> lst = service.selectMemPointList(param);
		log.info("search productlist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/point/buy_list");
		return mv;		
	}	
}
