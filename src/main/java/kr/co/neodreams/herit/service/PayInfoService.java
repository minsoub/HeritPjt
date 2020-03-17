package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.PayInfoMapper;
import kr.co.neodreams.herit.model.Member;
import kr.co.neodreams.herit.model.PayInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * PayInfo Service class
 * 
 * @author minsoub
 *
 */
public class PayInfoService {
	@Autowired
	private PayInfoMapper mapper;
	
	/**
	 * search the  Pay history list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<PayInfo> selectPayInfoList(PayInfo data) throws Exception
	{
		List<PayInfo> result = mapper.selectPayInfoList(data);
		log.info("selectPayInfoList : {}", result);
		
		return result;
	}

	/**
	 * search the Pay history List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectPayInfoListCount(PayInfo data) throws Exception
	{
		int result = mapper.selectPayInfoListCount(data);
		log.info("selectPayInfoListCount : {}", result);
		
		return result;
	}
}
