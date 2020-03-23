package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.ChkInfo;


/**
 * 검진결과 Mapper interface class
 * 
 * @author hist
 *
 */
@Mapper
public interface ChkInfoMapper {

	/**
	 * Key에 의해서 검진결과 상세 현황을 조회한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	ChkInfo selectChkInfoById(ChkInfo data) throws Exception;
	
	/**
	 * Paging을 위한 검진결과 리스트 총 개수를 리턴한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectChkInfoCount(ChkInfo data) throws Exception;
	
	/**
	 * 검진결과 리스트를 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<ChkInfo> selectChkInfoList(ChkInfo data) throws Exception;
	
	/**
	 * 검진결과를 등록한다. (신규 등록)
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertChkInfo(ChkInfo data) throws Exception;
	
	/**
	 * 검진결과 데이터를 수정한다. 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateChkInfo(ChkInfo data) throws Exception;
	
	/**
	 * 검진결과 데이터를 삭제한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteChkInfo(ChkInfo data) throws Exception;
}
