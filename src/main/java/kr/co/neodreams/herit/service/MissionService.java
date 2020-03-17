package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.MissionMapper;
import kr.co.neodreams.herit.model.Mission;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * Mission Service class
 * 
 * @author minsoub
 *
 */
public class MissionService {
	@Autowired
	private MissionMapper mapper;
	
	/**
	 * search the  Mission history list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Mission> selectMissionList(Mission data) throws Exception
	{
		List<Mission> result = mapper.selectMissionList(data);
		log.info("selectMissionList : {}", result);
		
		return result;
	}

	/**
	 * search the Mission history List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectMissionListCount(Mission data) throws Exception
	{
		int result = mapper.selectMissionListCount(data);
		log.info("selectMissionListCount : {}", result);
		
		return result;
	}
}
