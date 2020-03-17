package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.PayInfo;

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
	 * search the Pay history count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectPayInfoListCount(PayInfo data) throws Exception;
}
