package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Admin;

@Mapper
public interface LoginMapper {

	/**
	 * 관리자 로그인 정보 조회
	 * 
	 * @param id
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	Admin selectAdminLoginById(String id, String pass) throws Exception;
	
	/**
	 * 관리자 상세 정보 조회
	 * 
	 * @param id
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	Admin selectAdminById(String id, String pass) throws Exception;
	
	/**
	 * 전체 관리자 리스트 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Admin> selectAdminAll() throws Exception;
	
	/**
	 * 관리자 신규 등록
	 * 
	 * @param data
	 * @throws Exception
	 */
	void insertAdmin(Admin data) throws Exception;
	
	/**
	 * 관리자 정보 삭제
	 * 
	 * @param data
	 * @throws Exception
	 */
	void deleteAdmin(Admin data) throws Exception;
	
	/**
	 * 관리자 정보 수정
	 * 
	 * @param data
	 * @throws Exception
	 */
	void updateAdmin(Admin data) throws Exception;
	
}
