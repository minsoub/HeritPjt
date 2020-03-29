package kr.co.neodreams.herit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.MemPointMapper;
import kr.co.neodreams.herit.model.MemPoint;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * Member Point Service class
 * 
 * @author minsoub
 *
 */
public class MemPointService {
	@Autowired
	private MemPointMapper mapper;
	
	/**
	 * 포인트 현황 전체 누적 현황을 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map selectPointTotal(MemPoint data) throws Exception
	{
		Map result = mapper.selectPointTotal(data);
		log.info("selectPointTotal : {}", result);
		
		return result;
	}
	
	/**
	 * 포인트 현황 년간(월별) 내역을 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> selectPointYearData(MemPoint data) throws Exception
	{
		List<Map> result = mapper.selectPointYearData(data);
		log.info("selectPointYearData : {}", result);
		
		return result;
	}
	
	/**
	 * 포인트 현황 년간 SUM 내역을 조회한다. 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map selectPointYearDataSum(MemPoint data) throws Exception
	{
		Map result = mapper.selectPointYearDataSum(data);
		log.info("selectPointYearDataSum : {}", result);
		
		return result;
	}
	
	
	/**
	 * search the  Member Point history list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<MemPoint> selectMemPointList(MemPoint data) throws Exception
	{
		List<MemPoint> result = mapper.selectMemPointList(data);
		log.info("selectMemPointList : {}", result);
		
		return result;
	}

	/**
	 * search the Member Point history List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectMemPointListCount(MemPoint data) throws Exception
	{
		int result = mapper.selectMemPointListCount(data);
		log.info("selectMemPointListCount : {}", result);
		
		return result;
	}
	
	/**
	 * search the Member Point detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public MemPoint selectMemPointById(MemPoint data) throws Exception
	{
		MemPoint info = mapper.selectMemPointById(data);
		
		log.info("selectMemPointById : {}", info);
		return info;
	}
	
	/**
	 * search the  Private Member Point detail  list count
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<MemPoint> selectMemPointByIdList(MemPoint data) throws Exception
	{
		List<MemPoint> result = mapper.selectMemPointByIdList(data);
		log.info("selectMemPointByIdListCount : {}", result);
		
		return result;
	}

	/**
	 * search the Private Member Point history List 
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectMemPointByIdListCount(MemPoint data) throws Exception
	{
		int result =  mapper.selectMemPointByIdListCount(data);
		log.info("selectMemPointByIdList : {}", result);
		
		return result;
	}	
}
