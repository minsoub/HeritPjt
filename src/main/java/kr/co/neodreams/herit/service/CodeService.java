package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.ComcodeMapper;
import kr.co.neodreams.herit.model.Comcode;
import lombok.extern.slf4j.Slf4j;

/**
 * Common code service class
 * 
 * @author minso
 *
 */
@Slf4j
@Service
public class CodeService {

	@Autowired
	private ComcodeMapper mapper;
	
	/**
	 * search code by code group.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Comcode> selectCodeByGroup(String data) throws Exception
	{
		List<Comcode> lst = mapper.selectCodeByGroup(data);
		
		log.info("selectCodeByGroup : {}", lst);
		return lst;
	}
}
