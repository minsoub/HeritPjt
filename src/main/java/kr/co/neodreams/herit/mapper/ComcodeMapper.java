package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Comcode;

@Mapper
public interface ComcodeMapper {
	/**
	 * search code by code group.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Comcode> selectCodeByGroup(String data) throws Exception;
}
