package kr.co.neodreams.herit.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.AdminMapper;
import kr.co.neodreams.herit.model.Admin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * fetch the Authority data with the ID and password
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Admin selectAdminLoginById(Admin data) throws Exception
	{
		Admin info = adminMapper.selectAdminLoginById(data);
		
		log.info("selectAdminById : {}", info);
		return info;
	}
}
