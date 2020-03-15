package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.AdminMapper;
import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.AuthCheck;
import kr.co.neodreams.herit.model.Faq;
import kr.co.neodreams.herit.model.FaqCategory;
import lombok.extern.slf4j.Slf4j;


/**
 * Administrator Management Service class
 * @author minso
 *
 */
@Slf4j
@Service
public class AdminAuthorityService {

	@Autowired
	private AdminMapper mapper;
	
	/**
	 * return administrator list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Admin> selectAdminAll(Admin data) throws Exception
	{
		List<Admin> lst = mapper.selectAdminAll(data);
		
		log.info("selectAdminAll : {}", lst);
		return lst;
	}
	
	/**
	 * return administrator list count
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectAdminAllCount(Admin data) throws Exception
	{
		int cnt = mapper.selectAdminAllCount(data);
		
		return cnt;
	}

	/**
	 * return administrator detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Admin selectAdminById(Admin data) throws Exception
	{
		Admin info = mapper.selectAdminById(data);
		log.info("selectAdminById : {}", info);
		return info;
	}
	
	/**
	 * register new administrator
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertAdmin(Admin data) throws Exception
	{
		int retVal = mapper.insertAdmin(data);
		
		return retVal;
	}
	
	/**
	 * update the administrator data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateAdmin(Admin data) throws Exception
	{
		int retVal = mapper.updateAdmin(data);
		
		return retVal;
	}
	
	/**
	 * delete the administrator data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteAdmin(Admin data) throws Exception
	{
		int retVal = mapper.deleteAdmin(data);
		
		return retVal;
	}	
	
	
	
	
	/**
	 * search the all administrator authority data list.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<AuthCheck> selectAuthorityAll(AuthCheck data) throws Exception
	{
		List<AuthCheck> lst = mapper.selectAuthorityAll(data);
		
		log.info("selectAuthorityAll : {}", lst);
		return lst;
	}
	
	/**
	 * search the administrator authority detail data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public AuthCheck selectAuthorityById(AuthCheck data) throws Exception
	{
		AuthCheck info = mapper.selectAuthorityById(data);
		log.info("selectAuthorityById : {}", info);
		return info;
	}
	
	/**
	 * register the administrator authority data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertAuthority(AuthCheck data) throws Exception
	{
		int retVal = mapper.insertAuthority(data);
		
		return retVal;
	}
	
	/**
	 * update the administrator authority data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateAuthority(AuthCheck data) throws Exception
	{
		int retVal = mapper.updateAuthority(data);
		
		return retVal;
	}
	
	/**
	 * delete the administrator authority data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthority(AuthCheck data) throws Exception
	{
		int retVal = mapper.deleteAuthority(data);
		
		return retVal;
	}	
	
	/**
	 * search the all FAQ list.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Faq> selectFaqAll(Faq data) throws Exception
	{
		List<Faq> lst = mapper.selectFaqAll(data);
		
		log.info("selectFaqAll : {}", lst);
		return lst;
	}
	/**
	 * search the all FAQ list count
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectFaqAllCount(Faq data) throws Exception
	{
		int count = mapper.selectFaqAllCount(data);
		
		return count;
	}
	
	/**
	 * search the FAQ detail data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Faq selectFaqById(Faq data) throws Exception
	{
		Faq info = mapper.selectFaqById(data);
		log.info("selectFaqById : {}", info);
		return info;
	}	
	
	/**
	 * search the FAQ Category List
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<FaqCategory> selectFaqCategory() throws Exception
	{
		List<FaqCategory> lst = mapper.selectFaqCategory();
		
		log.info("selectFaqCategory : {}", lst);
		return lst;
	}
	
	/**
	 * replay the FAQ data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertFaq(Faq data) throws Exception
	{
		int retVal = mapper.updateFaqReply(data);
		
		return retVal;
	}
	
	
	/**
	 * delete the FAQ data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteFaq(Faq data) throws Exception
	{
		int retVal = mapper.deleteFaq(data);
		
		return retVal;
	}	
	
	/**
	 * return the FAQ category max seq number
	 * 
	 * @return
	 * @throws Exception
	 */
	public int selectFaqCategorySeq() throws Exception
	{
		int retVal = mapper.selectFaqCategorySeq();
		return retVal;
	}
	
	/**
	 * insert the FAQ Category data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertFaqCategory(FaqCategory data) throws Exception
	{
		int retVal = mapper.insertFaqCategory(data);
		return retVal;
	}
	/**
	 * update the FAQ Category data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateFaqCategory(FaqCategory data) throws Exception
	{
		int retVal = mapper.updateFaqCategory(data);
		
		return retVal;
	}
	/**
	 * delete the FAQ Category data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteFaqCategory(FaqCategory data) throws Exception
	{
		int retVal = mapper.deleteFaqCategory(data);
		
		return retVal;
	}
}
