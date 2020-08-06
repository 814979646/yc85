package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bean.Product;

public interface ProductMapper {

	List<Product> selectAll();
	
	Product selectById(int id);
	
	List<Product> selectByCid(int cid);
	
	List <Product> selectByObj(Product dp);
	
	List<Product> selectByCids(@Param("cids") int[] cids);

	int update(Product dp);
}
