package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.ChkInfoMapper;
import kr.co.neodreams.herit.model.ChkInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 건강검진 Service class
 * 
 * @author hist
 *
 */
@Slf4j
@Service
public class ChkService {
	@Autowired
	private ChkInfoMapper mapper;
	
	/**
	 * 사용자에 의해서 검진결과 리스트를 조회한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<ChkInfo> selectChkInfoByUserId(ChkInfo data) throws Exception
	{
		List<ChkInfo> result = mapper.selectChkInfoByUserId(data);
		
		return result;
	}
	
	/**
	 * Key에 의해서 검진결과 상세 현황을 조회한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public ChkInfo selectChkInfoById(ChkInfo data) throws Exception
	{
		ChkInfo result = mapper.selectChkInfoById(data);
		
		return result;
	}
	
	/**
	 * Paging을 위한 검진결과 리스트 총 개수를 리턴한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectChkInfoCount(ChkInfo data) throws Exception
	{
		int result = mapper.selectChkInfoCount(data);
		
		return result;
	}
	
	/**
	 * 검진결과 리스트를 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<ChkInfo> selectChkInfoList(ChkInfo data) throws Exception
	{
		List<ChkInfo> result = mapper.selectChkInfoList(data);
		
		return result;
	}
	
	/**
	 * 검진결과를 등록한다. (신규 등록)
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertChkInfo(ChkInfo data) throws Exception
	{
		int result = mapper.insertChkInfo(data);
		
		return result;
	}
	
	/**
	 * 검진결과 데이터를 수정한다. 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateChkInfo(ChkInfo data) throws Exception
	{
		int result = mapper.updateChkInfo(data);
		
		return result;
	}
	
	/**
	 * 검진결과 데이터를 삭제한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteChkInfo(ChkInfo data) throws Exception
	{
		int result = mapper.deleteChkInfo(data);
		
		return result;
	}
}
