package kr.co.neodreams.herit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.PayInfoMapper;
import kr.co.neodreams.herit.model.Member;
import kr.co.neodreams.herit.model.PayInfo;
import kr.co.neodreams.herit.model.PayPlanInfo;
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
	 * search the Pay History list for excel download.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<PayInfo> selectPayExcelList(PayInfo data) throws Exception
	{
		List<PayInfo> result = mapper.selectPayExcelList(data);
		log.info("selectPayExcelList : {}", result);
		
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
	/**
	 * Update the Pay history Status (Send)
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updatePayInfo(PayInfo data) throws Exception
	{
		int result = mapper.updatePayInfo(data);
		return result;
	}
	
	/**
	 * 결제 현황 리스트 출력 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> selectPayStsList(PayPlanInfo data) throws Exception
	{
		List<Map> result = mapper.selectPayStsList(data);
		log.info("selectPayStsList : {}", result);
		
		return result;
	}
	/**
	 * 결제 현황 리스트 합계를 출력한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map selectPayStsSum(PayPlanInfo data) throws Exception
	{
		Map result = mapper.selectPayStsSum(data);
		log.info("selectPayStsSum : {}", result);
		
		return result;
	}	
	
	/**
	 * search the  Pay Plan information  list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<PayPlanInfo> selectPayPlanList(PayPlanInfo data) throws Exception
	{
		List<PayPlanInfo> result = mapper.selectPayPlanList(data);
		log.info("selectPayInfoList : {}", result);
		
		return result;
	}
	
	/**
	 * search the Pay Plan information List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectPayInfoListCount(PayPlanInfo data) throws Exception
	{
		int result = mapper.selectPayPlanListCount(data);
		log.info("selectPayInfoListCount : {}", result);
		
		return result;
	}
	/**
	 * search the Pay Plan detail information. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public PayPlanInfo selectPayPlanById(PayPlanInfo data) throws Exception
	{
		PayPlanInfo result = mapper.selectPayPlanById(data);
		log.info("selectPayPlanById : {}", result);
		return result;
	}
	
	/**
	 * Insert the Pay plan data.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertPayPlan(PayPlanInfo data) throws Exception
	{
		int result = mapper.insertPayPlan(data);
		
		return result;
	}
	
	/**
	 * Update the Pay Plan data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updatePayPlan(PayPlanInfo data) throws Exception
	{
		int result = mapper.updatePayPlan(data);
		
		return result;
	}
	
	/**
	 * Delete the Pay Plan data
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deletePayPlan(PayPlanInfo data) throws Exception
	{
		int result = mapper.deletePayPlan(data);
		
		return result;
	}
	
	
	/**
	 * 선택한 요금제를 결제한 회원 리스트 총 인원 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectPayListCountByPayPlan(PayInfo data) throws Exception
	{
		int result = mapper.selectPayListCountByPayPlan(data);
		
		return result;
	}
	
	/**
	 * 선택한 요금제 결제한 회원 리스트 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<PayInfo> selectPayListByPayPlan(PayInfo data) throws Exception
	{
		List<PayInfo> result = mapper.selectPayListByPayPlan(data);
		
		return result;
	}
	
	/**
	 * 요금제 결제한 개별 회원에 대한 상세 현황 출력 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPayListById(PayInfo data) throws Exception
	{
		List<Map<String, Object>> result = mapper.selectPayListById(data);
		
		return result;
	}
	/**
	 * 선택한 개별 회원의 결제 내역을 취소한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deletePayByIdandSeq(PayInfo data) throws Exception
	{
		int result = mapper.deletePayByIdandSeq(data);
		
		return result;
	}
}
