package kr.co.neodreams.herit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Hospital;
import kr.co.neodreams.herit.model.MemPoint;

@Mapper
public interface MemPointMapper {
	
	
	/**
	 * 포인트 현황 전체 누적 현황을 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Map selectPointTotal(MemPoint data) throws Exception;
	
	/**
	 * 포인트 현황 년간(월별) 내역을 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Map> selectPointYearData(MemPoint data) throws Exception;
	
	/**
	 * 포인트 현황 년간 Sum 내역을 조회한다.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Map selectPointYearDataSum(MemPoint data) throws Exception;
	
	/**
	 * search the Point history list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<MemPoint> selectMemPointList(MemPoint data) throws Exception;

	/**
	 * search the Point history count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectMemPointListCount(MemPoint data) throws Exception;
	
	/**
	 * search the Member Point detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	MemPoint selectMemPointById(MemPoint data) throws Exception;
	
	/**
	 * search the private detail point list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<MemPoint> selectMemPointByIdList(MemPoint data) throws Exception;

	/**
	 * search the private detail point list count
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectMemPointByIdListCount(MemPoint data) throws Exception;
}
