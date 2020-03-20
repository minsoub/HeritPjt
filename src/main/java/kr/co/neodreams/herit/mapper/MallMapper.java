package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.MallInfo;

/**
 * Point Mall Interface mapper class
 * 
 * @author hist
 *
 */
@Mapper
public interface MallMapper {
	/**
	 * search the Point Mall detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	MallInfo selectMallById(MallInfo data) throws Exception;
	
	/**
	 * search the Point Mall list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<MallInfo> selectMallList(MallInfo data) throws Exception;

	/**
	 * search the Point Mall List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectMallListCount(MallInfo data) throws Exception;
	
	/**
	 * return total Point Mall count
	 * 
	 * @return
	 * @throws Exception
	 */
	int selectMallTotal() throws Exception;
	
	/**
	 * register the Point Mall information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertMall(MallInfo data) throws Exception;
	
	/**
	 * update the Product information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateMall(MallInfo data) throws Exception;
	
	/**
	 * delete the Point Mall information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteMall(MallInfo data) throws Exception;
}
