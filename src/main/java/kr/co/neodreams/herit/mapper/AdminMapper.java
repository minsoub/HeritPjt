package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Admin;
import kr.co.neodreams.herit.model.AuthCheck;
import kr.co.neodreams.herit.model.Faq;
import kr.co.neodreams.herit.model.FaqCategory;

/**
 * the mapper interface class for the administrator management.
 * 
 * @author hist
 *
 */
@Mapper
public interface AdminMapper {
	/**
	 * 관리자 로그인 정보 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Admin selectAdminLoginById(Admin data) throws Exception;
	
	/**
	 * update the last login date of administrator.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateAdminLoginDt(Admin data) throws Exception;
	
	/**
	 * 관리자 상세 정보 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Admin selectAdminById(Admin data) throws Exception;
	
	/**
	 * 전체 관리자 리스트 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Admin> selectAdminAll(Admin data) throws Exception;
	
	/**
	 * return All administrator list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectAdminAllCount(Admin data) throws Exception;
	
	/**
	 * 관리자 신규 등록
	 * 
	 * @param data
	 * @throws Exception
	 */
	int insertAdmin(Admin data) throws Exception;
	
	/**
	 * 관리자 정보 삭제
	 * 
	 * @param data
	 * @throws Exception
	 */
	int deleteAdmin(Admin data) throws Exception;
	
	/**
	 * 관리자 정보 수정
	 * 
	 * @param data
	 * @throws Exception
	 */
	int updateAdmin(Admin data) throws Exception;	
	
	
	/**
	 * search the administrator authority list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<AuthCheck> selectAuthorityAll(AuthCheck data) throws Exception;
	
	/**
	 * search the administrator authority detail data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	AuthCheck selectAuthorityById(AuthCheck data) throws Exception;
	
	/**
	 * register the administrator authority data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertAuthority(AuthCheck data) throws Exception;
	
	/**
	 * modify the administrator authority data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateAuthority(AuthCheck data) throws Exception;
	
	/**
	 * delete the administrator authority data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteAuthority(AuthCheck data) throws Exception;
	
	/**
	 * search the FAQ list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Faq> selectFaqAll(Faq data) throws Exception;	
	
	/**
	 * search the FAQ List count
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectFaqAllCount(Faq data) throws Exception;
	
	/**
	 * search the FAQ Category List
	 * 
	 * @return
	 * @throws Exception
	 */
	List<FaqCategory> selectFaqCategory() throws Exception;
	
	/**
	 * return the faq category max seq number
	 * 
	 * @return
	 * @throws Exception
	 */
	int selectFaqCategorySeq() throws Exception;
	
	/**
	 * search the FAQ detail data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Faq selectFaqById(Faq data) throws Exception;
	
	/**
	 * register the FAQ replay data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateFaqReply(Faq data) throws Exception;
	
	
	/**
	 * delete the FAQ replay data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteFaq(Faq data) throws Exception;	
	
	/**
	 * insert the FAQ Category data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertFaqCategory(FaqCategory data) throws Exception;
	
	/**
	 * update the FAQ Category data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateFaqCategory(FaqCategory data) throws Exception;
	
	/**
	 * delete the FAQ Category data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteFaqCategory(FaqCategory data) throws Exception;
}
