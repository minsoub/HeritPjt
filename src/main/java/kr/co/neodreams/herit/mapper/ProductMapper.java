package kr.co.neodreams.herit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.neodreams.herit.model.Product;

@Mapper
public interface ProductMapper {
	/**
	 * search the Product detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Product selectProductById(Product data) throws Exception;
	
	/**
	 * search the Product list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Product> selectProductList(Product data) throws Exception;

	/**
	 * search the Product List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int selectProductListCount(Product data) throws Exception;
	
	/**
	 * search the Product All list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Product> selectProductAllList() throws Exception;
	
	/**
	 * return total Product count
	 * 
	 * @return
	 * @throws Exception
	 */
	int selectProductTotal() throws Exception;
	
	/**
	 * register the Product information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int insertProduct(Product data) throws Exception;
	
	/**
	 * update the Product information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int updateProduct(Product data) throws Exception;
	
	/**
	 * delete the Product information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	int deleteProduct(Product data) throws Exception;
}
