package kr.co.neodreams.herit.mapper;

import java.util.List;

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
	Product selectPayPlanById(PayPlanInfo data) throws Exception;
	
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
}
