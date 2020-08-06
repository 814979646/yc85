package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import bean.Product;
import bean.ttsx_category;

import org.junit.Assert;

public class ProductMapperTest  {
	
	private SqlSession session;
	{
		try {
			String resource="mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session=sqlSessionFactory.openSession();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test1() throws IOException {
		//String resource = "mybatis.xml";
		//InputStream inputStream = Resources.getResourceAsStream(resource);
		//SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//SqlSession session=sqlSessionFactory.openSession();
		List<Product> list=session.selectList("dao.ProductMapper.selectAll");
		
		Assert.assertEquals(true, list.size()>0);
		/**
		 * for(Product dp:list) {
		 * 	System.out.println(dp);}
		 */
	}
	
	@Test
	public void test2() throws IOException{
		ttsx_category t=new ttsx_category();
		t.setCname("测试分类");
		t.setPid(10);
		//session.insert("dao.CategoryMapper.insert",t);
		CategoryMapper mapper=session.getMapper(CategoryMapper.class);
		mapper.insert(t);
		session.commit();
		session.close();
	
	}
	
	@Test
	public void test3() throws IOException{
		ttsx_category t=new ttsx_category();
		t.setId(12);
		t.setCname("修改后的测试分类");
		t.setPid(11);
		//session.update("dao.CategoryMapper.update",t);
		CategoryMapper mapper=session.getMapper(CategoryMapper.class);
		mapper.update(t);
		session.commit();
		session.close();
	}
	
	@Test
	public void test4() throws IOException{
		//ttsx_category t=new ttsx_category();
		//t.setId(8);
		//t.setCname("修改后的测试分类");
		//t.setPid(10);
		//session.delete("dao.CategoryMapper.delete",t);
		CategoryMapper mapper=session.getMapper(CategoryMapper.class);	
		mapper.delete(13);
		session.commit();
		session.close();
	}
	
	@Test
	public void test5() throws IOException{
		//Product t=new Product();
		//t.setId(1);
		//session.selectList("dao.CategoryMapper.select",t);
		CategoryMapper mapper=session.getMapper(CategoryMapper.class);
		mapper.selectAll();
		session.commit();
		session.close();
	}
	
	@Test
	public void test6() throws IOException{
		
		CategoryMapper dom=session.getMapper(CategoryMapper.class);
		ProductMapper doi=session.getMapper(ProductMapper.class);
		ttsx_category cp=dom.selectById(2);
		List<Product> dp=doi.selectByCid(cp.getId());
		System.out.println(dp);
		session.close();
	}
	
	@Test
	public void test7() throws IOException{
		
		CategoryMapper dom=session.getMapper(CategoryMapper.class);
		ttsx_category cp=dom.selectById(8);
		List<Product> dp=cp.getProducts();
		System.out.println(dp);
		session.close();
	}
	
	@Test
	public void test8() throws IOException{
		CategoryMapper dom=session.getMapper(CategoryMapper.class);
		List<ttsx_category> cp=dom.selectAll();
		System.out.println("=======1==========");
		ttsx_category dc=cp.get(1);
		System.out.println("=======2==========");
		Assert.assertEquals("海鲜水产", dc.getCname());
		System.out.println("=======3==========");
		Assert.assertEquals(3, dc.getChildren().size());
		System.out.println("=======4==========");
	}
	
	@Test
	public void test9() throws IOException{
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		System.out.println("=======1==========");
		mapper.selectByObj(null);
		Product dp=new Product();
		System.out.println("=======2==========");
		mapper.selectByObj(dp);
		
		dp.setProductname("草莓");
		System.out.println("=======3==========");
		mapper.selectByObj(dp);
		
		dp.setProducts("品味鲜果滋味，让畅享淋漓尽致");
		System.out.println("=======4==========");
		mapper.selectByObj(dp);
		
		dp.setIshot(-1);
		System.out.println("=======5==========");
		mapper.selectByObj(dp);
		
		dp.setIshot(1);
		System.out.println("=======6==========");
		mapper.selectByObj(dp);
	}
	
	@Test
	public void test10() throws IOException{
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		int [] cids= {1,2,3};
		mapper.selectByCids(cids);
	}
	
	@Test
	public void test11() throws IOException{
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		Product dp=new Product();
		dp.setId(1);
		dp.setPrice(20);
		mapper.update(dp);
		
		Product dbdp=mapper.selectById(1);
		
		Assert.assertEquals((Integer)20, dbdp.getPrice());
		Assert.assertEquals((Integer)1, dbdp.getCid());
		Assert.assertEquals("海南金煌芒果", dbdp.getProductname());
	}
}
