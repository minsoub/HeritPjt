package kr.co.neodreams.herit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.MemberMapper;
import kr.co.neodreams.herit.model.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * Member Service class
 * 
 * @author minsoub
 *
 */
public class MemberService {
	@Autowired
	private MemberMapper mapper;
	
	/**
	 * User login check
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Member selectLoginById(Member data) throws Exception
	{
		Member info = mapper.selectLoginById(data);
		
		log.info("selectLoginById : {}", info);
		return info;
	}
	
	/**
	 * search the User detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Member selectDetailById(Member data) throws Exception
	{
		Member info = mapper.selectMemberById(data);
		
		log.info("selectMemberById : {}", info);
		return info;
	}
	
	/**
	 * search the User list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Member data) throws Exception
	{
		List<Member> result = mapper.selectMemberList(data);
		log.info("selectMemberList : {}", result);
		
		return result;
	}

	/**
	 * search the User List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectMemberListCount(Member data) throws Exception
	{
		int result = mapper.selectMemberListCount(data);
		log.info("selectMemberListCount : {}", result);
		
		return result;
	}
	
	/**
	 * return total member count
	 * 
	 * @return
	 * @throws Exception
	 */
	public int selectMemberTotal(Member data) throws Exception
	{
		int result = mapper.selectMemberTotal(data);
		
		return result;
	}
	
	/**
	 * register the User information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertMember(Member data) throws Exception
	{
		int result = mapper.insertMember(data);
		
		return result;
	}
	
	/**
	 * update the User information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateMember(Member data) throws Exception
	{
		int result = mapper.updateMember(data);
		
		return result;
	}
	
	/**
	 * delete the User information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteMember(Member data) throws Exception
	{
		int result;
		if (data.getMem_sts().equals("0"))
		{
			result = mapper.deleteMember(data);	
		}else {
			result = mapper.updateStsMember(data);
		}
		
		
		return result;
	}
	
	/**
	 * 임시 비밀번호를 업데이트 한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateMemberPassword(Member data) throws Exception
	{
		int result = mapper.updateMemberPassword(data);
		return result;
	}
	
	/**
	 * 팝업이나 기타 화면에서 호출되는 사용자 검색 리스트 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMemberSearchList(Member data) throws Exception
	{
		List<Map<String, Object>> result = mapper.selectMemberSearchList(data);
		
		return result;
	}
}
