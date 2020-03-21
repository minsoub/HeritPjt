package kr.co.neodreams.herit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.neodreams.herit.mapper.ProductMapper;
import kr.co.neodreams.herit.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * Product Service class
 * 
 * @author minsoub
 *
 */
public class ProductService {
	@Autowired
	private ProductMapper mapper;
	
	/**
	 * search the Product detail information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Product selectProductById(Product data) throws Exception
	{
		Product result = mapper.selectProductById(data);
		log.info("selectProductById : {}", result);
		
		return result;
	}
	
	/**
	 * search the Product list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Product> selectProductList(Product data) throws Exception
	{
		List<Product> result = mapper.selectProductList(data);
		log.info("selectProductList : {}", result);
		
		return result;
	}

	
	/**
	 * search the Product All list
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Product> selectProductAllList() throws Exception
	{
		List<Product> result = mapper.selectProductAllList();
		log.info("selectProductAllList : {}", result);
		
		return result;
	}
	
	/**
	 * search the Product List count.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int selectProductListCount(Product data) throws Exception
	{
		int result = mapper.selectProductListCount(data);
		log.info("selectProductListCount : {}", result);
		
		return result;
	}
	
	/**
	 * return total Product count
	 * 
	 * @return
	 * @throws Exception
	 */
	public int selectProductTotal() throws Exception
	{
		int result = mapper.selectProductTotal();
		log.info("selectProductTotal : {}", result);
		
		return result;
	}
	
	/**
	 * register the Product information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insertProduct(Product data) throws Exception
	{
		int result = mapper.insertProduct(data);
		log.info("insertProduct : {}", result);
		
		return result;
	}
	
	/**
	 * update the Product information.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int updateProduct(Product data) throws Exception
	{
		int result = mapper.updateProduct(data);
		log.info("updateProduct : {}", result);
		
		return result;
	}
	
	/**
	 * delete the Product information
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int deleteProduct(Product data) throws Exception
	{
		int result = mapper.deleteProduct(data);
		log.info("deleteProduct : {}", result);
		
		return result;
	}
}
