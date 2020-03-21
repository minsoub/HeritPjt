package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.MallMapper;
import kr.co.neodreams.herit.model.MallInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * Point Mall Service class
 * 
 * @author minsoub
 *
 */
public class MallService {
	@Autowired
	private MallMapper mapper;
	
	/**
	 * search the Point Mall detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public MallInfo selectMallById(MallInfo data) throws Exception
	{
		MallInfo result = mapper.selectMallById(data);
		log.info("selectMallById : {}", result);
		
		return result;
	}
	
	/**
	 * search the Point Mall list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<MallInfo> selectMallList(MallInfo data) throws Exception
	{
		List<MallInfo> result = mapper.selectMallList(data);
		log.info("selectMallList : {}", result);
		
		return result;
	}

	/**
	 * search the Point Mall List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectMallListCount(MallInfo data) throws Exception
	{
		int result = mapper.selectMallListCount(data);
		log.info("selectMallListCount : {}", result);
		
		return result;
	}
	
	/**
	 * return total Point Mall count
	 * 
	 * @return
	 * @throws Exception
	 */
	public int selectMallTotal() throws Exception
	{
		int result = mapper.selectMallTotal();
		log.info("selectMallTotal : {}", result);
		
		return result;
	}
	
	/**
	 * register the Point Mall information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertMall(MallInfo data) throws Exception
	{
		int result = mapper.insertMall(data);
		log.info("insertMall : {}", result);
		
		return result;
	}
	
	/**
	 * update the Point Mall information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateMall(MallInfo data) throws Exception
	{
		int result = mapper.updateMall(data);
		log.info("updateMall : {}", result);
		
		return result;
	}
	
	/**
	 * delete the Point Mall information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteMall(MallInfo data) throws Exception
	{
		int result = mapper.deleteMall(data);
		log.info("deleteMall : {}", result);
		
		return result;
	}
}
