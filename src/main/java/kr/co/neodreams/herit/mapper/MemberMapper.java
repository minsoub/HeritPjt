package kr.co.neodreams.herit.mapper;

import java.util.List;

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
	Member selectDetailById(Member data) throws Exception;
	
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
}
