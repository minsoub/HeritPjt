package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
}
