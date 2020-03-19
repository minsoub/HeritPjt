package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Hospital;
import kr.co.neodreams.herit.model.MemPoint;

@Mapper
public interface MemPointMapper {
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
