package kr.co.neodreams.herit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Member;

/**
 * Member management interface map class
 * 
 * @author hist
 *
 */
@Mapper
public interface MemberMapper {
	/**
	 * User login check
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Member selectLoginById(Member data) throws Exception;
	
	/**
	 * search the User detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Member selectMemberById(Member data) throws Exception;
	
	/**
	 * search the User list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Member> selectMemberList(Member data) throws Exception;

	/**
	 * search the User List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectMemberListCount(Member data) throws Exception;
	
	/**
	 * return total member count
	 * 
	 * @return
	 * @throws Exception
	 */
	int selectMemberTotal() throws Exception;
	
	/**
	 * register the User information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertMember(Member data) throws Exception;
	
	/**
	 * update the User information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateMember(Member data) throws Exception;
	
	/**
	 * delete the User information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteMember(Member data) throws Exception;
	
	/**
	 * 임시 비밀번호를 저장한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateMemberPassword(Member data) throws Exception;
	
	/**
	 * 팝업이나 기타에서 사용되는 사용자 검색 리스트 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectMemberSearchList(Member data) throws Exception;
}
