package kr.co.neodreams.herit.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.service.MallService;
import kr.co.neodreams.herit.service.MemPointService;
import kr.co.neodreams.herit.service.PayInfoService;
import kr.co.neodreams.herit.service.ProductService;
import kr.co.neodreams.herit.util.ExcelUtil;
import kr.co.neodreams.herit.config.PropertyConfiguration;
import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.MallInfo;
import kr.co.neodreams.herit.model.MemPoint;
import kr.co.neodreams.herit.model.PayInfo;
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
//@EnableConfigurationProperties(PropertyConfiguration.class)
public class AdminMngPointController {
	@Autowired
    private PropertyConfiguration appConfig;
	@Value("${file.uploadpath}")
	private String uploadpath;
	
	@Autowired
	private MemPointService service;		// Point service
	@Autowired
	private ProductService  pService;		// Product service
	@Autowired
	private MallService		mService;		// Point Mall Service
	@Autowired
	private PayInfoService  yService;		// Pay history Service
	
	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;
	
	/**
	 * Point list page
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
	public ModelAndView productlist(Product param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = pService.selectProductListCount(param);
		List<Product> lst = pService.selectProductList(param);
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
		log.info("params : {}", param);
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
			
			param.setProduct_img(destinationFileName);
		}
		        
		// 신규/수정 처리
		if (param.getSeq() == 0)	// insert
		{
			param.setUse_yn("Y");		// use check
			result = pService.insertProduct(param);
		}else {
			result = pService.updateProduct(param);
		}
		
		mv.setViewName("redirect:productlist");
		return mv;
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
	@PostMapping("/productdelete")
	public void productDelete(HttpServletRequest req, HttpServletResponse res, Product param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("productdelete param {}", param);			
			retVal = Integer.toString(pService.deleteProduct(param));
			
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
	 * Point Mall list page (job need)
	 * 
	 * @return
	 */
	@RequestMapping("/malllist")
	public ModelAndView malllist(MallInfo param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = mService.selectMallListCount(param);
		List<MallInfo> lst = mService.selectMallList(param);
		log.info("search malllist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/point/mall_list");
		return mv;		
	}	
	
	/**
	 * Point Mall Register/Update Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mallregform")
	public ModelAndView malllRegForm(MallInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if (param.getSeq() > 0)
		{
			MallInfo info = mService.selectMallById(param);
			log.info("mallregform info : {}", info);
			mv.addObject("info", info);
		}else {
			mv.addObject("info", new MallInfo());
		}
		// Product List
		List<Product> lst = pService.selectProductAllList();
		mv.addObject("productList", lst);
		
		mv.setViewName("admin/point/mall_reg");
		return mv;
	}
	
	/***
	 * Insert/Update the Mall Point data 
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mallsave")
	public ModelAndView mallSave(MallInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result;
		        
		// 신규/수정 처리
		if (param.getSeq() == 0)	// insert
		{
			param.setUse_yn("Y");		// use check
			result = mService.insertMall(param);
		}else {
			result = mService.updateMall(param);
		}
		
		mv.setViewName("redirect:malllist");
		return mv;
	}	
	
	/**
	 * Mall Point Detail Form
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/malldetail")
	public ModelAndView mallDetail(MallInfo param, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("params : {}", param);
		MallInfo info = mService.selectMallById(param);
		log.info("mallDetail info : {}", info);
		mv.addObject("info", info);

		mv.setViewName("admin/point/mall_detail");
		return mv;
	}	
	
	/**
	 * delete the Mall Point data
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/malldelete")
	public void mallDelete(HttpServletRequest req, HttpServletResponse res, MallInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			param.setUse_yn("Y");
			log.debug("malldelete param {}", param);			
			retVal = Integer.toString(mService.deleteMall(param));
			
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
	 * Buy History list page  
	 * 
	 * @return
	 */
	@RequestMapping("/buylist")
	public ModelAndView buylist(PayInfo param, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("paramter : {}", param);
		
		param.setPageStartNo((param.getPageNo()-1) * param.getPerPageCnt());
		int cnt = yService.selectPayInfoListCount(param);
		List<PayInfo> lst = yService.selectPayInfoList(param);
		log.info("search buylist list : {}", lst);
		mv.addObject("totalCnt", cnt);		// need to Integer type

		mv.addObject("list", lst);
		mv.addObject("paging", param);
		mv.setViewName("admin/point/buy_list");
		return mv;		
	}	
	
	/**
	 * Buy History Status Update
	 * 
	 * @param req
	 * @param res
	 * @param param
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/buyupdate")
	public void buyUpdate(HttpServletRequest req, HttpServletResponse res, PayInfo param) throws Exception {
		
		String retVal = "0";
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try{
			log.debug("buyupdate param {}", param);			
			retVal = Integer.toString(yService.updatePayInfo(param));
			
			log.debug("retVal : {}", retVal);
			dataSourceTransactionManager.commit(status);
		}catch (Exception e) {
			retVal = "-1";
			log.debug("buyupdate error : {}", e);
			dataSourceTransactionManager.rollback(status);
		}finally {
			res.getWriter().write(retVal);
		}
	}	 
	/**
	 * Excel download of Pay History list.
	 * 
	 * @param param
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/buyExcelDownload")
	public @ResponseBody byte[] getBuyExcelDownload(PayInfo param, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Set-Cookie", "fileDownload=true); path=/");
		
		log.debug("parameter : {}", param);
		List<PayInfo> exList = yService.selectPayExcelList(param);
		// Excel 세팅
		List<Object> header = new ArrayList<Object>();
		List<List<Object>> data = new ArrayList<List<Object>>();
				
		String[] keyVal = {"번호", "결제일시", "사용자명", "결제상품", "결제금액", "수량", "총결제금액", "결제상태", "발송상태"};
		
		try {
			// Row 생성  Loop
			for (int i=0; i<keyVal.length; i++) header.add(keyVal[i]);
			
			for (int r=0; r<exList.size(); r++)
			{
				PayInfo map = exList.get(r);
				
				List<Object> obj = new ArrayList<Object>();
				// ${totalCnt - (paging.perPageCnt*(paging.pageNo-1)) - status.index }
				obj.add(String.valueOf( r+1 ) );  // 순번
				obj.add(map.getPay_dt());
				obj.add(map.getName());
				obj.add(map.getProduct_name());
				obj.add(map.getPay_amt());		// 결제금액
				obj.add(map.getPay_cnt());		// 수량
				obj.add(map.getPay_total());	// 총결제금액
				obj.add(map.getPay_sts_name());	// 결제상태
				obj.add(map.getSend_sts_name());	// 발송상태
				
				data.add(obj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		ExcelUtil excel = new ExcelUtil(header ,data);
		excel.setSheetName("sheet1");
		excel.setWidth(6000);
		
		byte[] bytes = excel.makeExcel();
		
        String userAgent = request.getHeader("User-Agent");
        boolean br = userAgent.indexOf("Chrome") > -1;
        
        String fileName = "구매내역_" + param.getPay_dt();
        String docName =""; 
        
        if(br){
        	docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } else {
        	docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
        }
		
		response.setHeader("Content-Disposition", "attachment; filename="+docName+".xlsx");
		response.setContentLength(bytes.length);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");		
		response.setHeader("Cache-Control", "private");
		response.setHeader("Expires", "0");
		
		return bytes;
	}
}
