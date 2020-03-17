package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Hospital;


/**
 * Hospital Mapper Class
 * 
 * @author minso
 *
 */
@Mapper
public interface HospitalMapper {
	/**
	 * search the Hospital detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Hospital selectHospitalById(Hospital data) throws Exception;
	
	/**
	 * search the Hospital list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Hospital> selectHospitalList(Hospital data) throws Exception;

	/**
	 * search the Hospital List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectHospitalListCount(Hospital data) throws Exception;
	
	/**
	 * return total Hospital count
	 * 
	 * @return
	 * @throws Exception
	 */
	int selectHospitalTotal() throws Exception;
	
	/**
	 * register the Hospital information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertHospital(Hospital data) throws Exception;
	
	/**
	 * update the Hospital information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateHospital(Hospital data) throws Exception;
	
	/**
	 * delete the Hospital information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteHospital(Hospital data) throws Exception;
}
