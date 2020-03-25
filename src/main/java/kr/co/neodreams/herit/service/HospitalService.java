package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.HospitalMapper;
import kr.co.neodreams.herit.model.Hospital;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * Hospital Service class
 * 
 * @author minsoub
 *
 */
public class HospitalService {

	@Autowired
	private HospitalMapper mapper;
	/**
	 * search the Hospital detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Hospital selectHospitalById(Hospital data) throws Exception
	{
		Hospital info = mapper.selectHospitalById(data);
		
		log.info("selectHospitalById : {}", info);
		return info;
	}
	
	/**
	 * search the Hospital All
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Hospital> selectHospitalAll() throws Exception
	{
		List<Hospital> result = mapper.selectHospitalAll();
		
		return result;
	}
	
	/**
	 * search the Hospital list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Hospital> selectHospitalList(Hospital data) throws Exception
	{
		List<Hospital> result = mapper.selectHospitalList(data);
		
		return result;
	}

	/**
	 * search the Hospital List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectHospitalListCount(Hospital data) throws Exception
	{
		int result = mapper.selectHospitalListCount(data);
		
		return result;
	}
		
	/**
	 * register the Hospital information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertHospital(Hospital data) throws Exception
	{
		int result = mapper.insertHospital(data);
		
		return result;
	}
	
	/**
	 * update the Hospital information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateHospital(Hospital data) throws Exception
	{
		int result = mapper.updateHospital(data);
		
		return result;
	}
	
	/**
	 * delete the Hospital information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteHospital(Hospital data) throws Exception
	{
		int result = mapper.deleteHospital(data);
		
		return result;
	}
}
