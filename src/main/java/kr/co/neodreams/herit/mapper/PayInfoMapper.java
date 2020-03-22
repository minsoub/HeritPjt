package kr.co.neodreams.herit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.PayInfo;
import kr.co.neodreams.herit.model.PayPlanInfo;
import kr.co.neodreams.herit.model.Product;

@Mapper
public interface PayInfoMapper {
	/**
	 * search the Pay history list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<PayInfo> selectPayInfoList(PayInfo data) throws Exception;
	
	/**
	 * search the Pay History list for excel download.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<PayInfo> selectPayExcelList(PayInfo data) throws Exception;

	/**
	 * search the Pay history count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectPayInfoListCount(PayInfo data) throws Exception;
	
	/**
	 * Update the pay history status.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updatePayInfo(PayInfo data) throws Exception;
	
	
	/**
	 * search the Pay Plan detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	PayPlanInfo selectPayPlanById(PayPlanInfo data) throws Exception;
	
	/**
	 * 결제현황 리스트 출력 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	List<Map> selectPayStsList(PayPlanInfo data) throws Exception;
	
	/**
	 * 결제현황 리스트 합계를 출력한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Map selectPayStsSum(PayPlanInfo data) throws Exception;
		
	
	/**
	 * search the Pay Plan list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<PayPlanInfo> selectPayPlanList(PayPlanInfo data) throws Exception;

	/**
	 * search the Pay Plan List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectPayPlanListCount(PayPlanInfo data) throws Exception;
	
	/**
	 * search the Pay Plan All list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<PayPlanInfo> selectPayPlanAllList() throws Exception;
	
	/**
	 * return total Pay Plan count
	 * 
	 * @return
	 * @throws Exception
	 */
	int selectPayPlanTotal() throws Exception;
	
	/**
	 * register the Pay Plan information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertPayPlan(PayPlanInfo data) throws Exception;
	
	/**
	 * update the Pay Plan information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updatePayPlan(PayPlanInfo data) throws Exception;
	
	/**
	 * delete the Pay Plan information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deletePayPlan(PayPlanInfo data) throws Exception;	
	
	/**
	 * 선택한 요금제를 결제한 회원 리스트 총 인원 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectPayListCountByPayPlan(PayInfo data) throws Exception;
	
	/**
	 * 선택한 요금제 결제한 회원 리스트 조회
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<PayInfo> selectPayListByPayPlan(PayInfo data) throws Exception;
	
	/**
	 * 요금제 결제한 개별 회원에 대한 상세 현황 출력 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectPayListById(PayInfo data) throws Exception;
	
	/**
	 * 선택한 개별 회원 결제 내역을 취소 등록한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deletePayByIdandSeq(PayInfo data) throws Exception;
}
