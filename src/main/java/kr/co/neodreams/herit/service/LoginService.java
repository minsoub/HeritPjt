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
	
	public Admin selectAdminLoginById(String id, String pass) throws Exception
	{
		Admin info = adminMapper.selectAdminById(id, pass);
		
		log.info("selectAdminById : {}", info);
		return info;
	}
}
