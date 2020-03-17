package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Mission;

@Mapper
public interface MissionMapper {
	/**
	 * search the Mission history list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Mission> selectMissionList(Mission data) throws Exception;

	/**
	 * search the Mission history count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectMissionListCount(Mission data) throws Exception;
}
