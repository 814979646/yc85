package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Product;
import util.DBhelper;

public class ProductDao {
	
	public List<Product> querymax(String cid) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_product where cid=? ";
		return dbh.query(sql,Product.class, cid);

	}
	public List<Product> queryxlmax(String cid) {
		DBhelper dbh = new DBhelper();
		String sql = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	ttsx_product\n" +
				"WHERE\n" +
				"	cid = ?\n" +
				"ORDER BY\n" +
				"	xl DESC\n" +
				"LIMIT 0,\n" +
				" 4";
		return dbh.query(sql,Product.class, cid);

	}
	
	public void updatexl(Integer xl,String id) {
		DBhelper dbh = new DBhelper();
		String sql = "update ttsx_product set xl=? where id=?";
		dbh.update(sql, xl, id);

	}

	public Map<String, Object> getProduct(String id) {
		String sql = "select * from ttsx_product where id=?";
		return new DBhelper().query(sql, id).get(0);
	}

	public void save(String id, String productname, String price, String cid, String specs, String images,String products,String ishot,String productinfo) {
		String sql = "update ttsx_product set productname=?,"
				+ "price=?,cid=?,specs=?,images=?,products=?,ishot=?,productinfo=? where id=?";
		new DBhelper().update(sql, productname, price, cid, specs, images, products,ishot,productinfo, id);
		
	}


	public void insert(String productname, String price, String cid, String specs, String images, String  products,String productinfo) {
		DBhelper dbh = new DBhelper();
		String sql = "insert ttsx_product values(null,?,?,?,?,?,?,now(),0,?,0)";
		dbh.update(sql, productname, price, cid, specs, images, products,productinfo);
	}
	public void insert(Product dp) {
		DBhelper dbh = new DBhelper();
		String sql = "insert ttsx_product values(null,?,?,?,?,?,?,now(),0,?,0)";
		dbh.update(sql, dp.getProductname(),
				dp.getPrice(),
				dp.getCid(), dp.getSpecs(), dp.getImages(), dp.getProducts(),
				dp.getProductinfo());
	}

	public void delete(String id) {
		DBhelper dbh = new DBhelper();
		String sql = "delete from ttsx_product where id=?";
		dbh.update(sql, id);
	}
	public List<Product> query(String cid) {
		DBhelper dbh = new DBhelper();
		String sql = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	ttsx_product\n" +
				"WHERE\n" +
				"	cid =?\n" +
				"AND ishot = 1\n" +
				"ORDER BY\n" +
				"	id DESC\n" +
				"LIMIT 0,\n" +
				" 4";
		return dbh.query(sql,Product.class, cid);

	}
	public int countPages(String cid){
		DBhelper dbh = new DBhelper();
		//System.out.println(cid);
		String sql="SELECT\n" +
				"	a.id\n" +
				"FROM\n" +
				"	ttsx_category a\n" +
				"LEFT JOIN ttsx_product b ON a.id = b.cid\n" +
				"WHERE\n" +
				"	b.cid = ?";
		int count=dbh.count(sql, cid);
		//System.out.println(count);
		if(count%20==0) {
			return count/20;
		}else {
			return count/20+1;
		}
		
	}
	public List<Map<String,Object>> querycid(String cid,int page) {
		DBhelper dbh = new DBhelper();
		int begin=(page-1)*20;
		String sql = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	ttsx_product\n" +
				"WHERE\n" +
				"	cid = ?\n" +
				"LIMIT ?,\n" +
				" ?";
		return dbh.query(sql, cid,begin,20);

	}
	public List<Map<String,Object>> querycid1(String cid,int page) {
		DBhelper dbh = new DBhelper();
		int begin=(page-1)*20;
		String sql ="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	ttsx_product\n" +
				"WHERE\n" +
				"	cid = ?\n" +
				"ORDER BY\n" +
				"	price ASC\n" +
				"LIMIT ?,\n" +
				" ?";
		return dbh.query(sql, cid,begin,20);

	}
	public List<Product> querynew(String cid) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_product where cid=? ORDER BY id desc LIMIT 0,4 ";
		return dbh.query(sql,Product.class, cid);

	}
	public List<Product> queryid(String id) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_product where id=?";
		return dbh.query(sql,Product.class, id);

	}
	public List<Product> querynew1(String id) {
		DBhelper dbh = new DBhelper();
		String sql = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	ttsx_product\n" +
				"WHERE\n" +
				"	cid = (\n" +
				"		SELECT\n" +
				"			cid\n" +
				"		FROM\n" +
				"			ttsx_product\n" +
				"		WHERE\n" +
				"			id = ?\n" +
				"	)\n" +
				"ORDER BY\n" +
				"	id DESC\n" +
				"LIMIT 0,\n" +
				" 4";
		return dbh.query(sql,Product.class, id);

	}
	public List<Product> seach(String productname) {
		DBhelper dbh = new DBhelper();
		String sql="select * from ttsx_product where productname like concat('%',?,'%')";
		return dbh.query(sql, Product.class, productname);
		
	}
	public Map<String,Object> queryid1(String id) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_product where id=?";
		return dbh.query(sql, id).get(0);

	}
	public Map<String,Object> querycid(String cid) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_category where id=?";
		return dbh.query(sql, cid).get(0);

	}
	public Map<String,Object> queryall(String productname) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_product where productname=?";
		return dbh.query(sql, productname).get(0);

	}
	public List<Product> queryxl(String productname) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_product where productname=?";
		return dbh.query(sql, Product.class, productname);

	}
	public int count1(Product dp){
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getProductname()!=null && dp.getProductname().trim().isEmpty() == false) {
			where += " and productname like ?";
			params.add("%" + dp.getProductname() + "%");
		}
		if(dp.getCid() != null &&dp.getCid() !=0) {
			where += " and cid = ?";
			params.add(dp.getCid());
		}

		if(dp.getIshot() != null &&dp.getIshot() !=0) {
			where += " and ishot = ?";
			params.add(dp.getIshot());
		}
		String sql = "select * from ttsx_product where 1=1" + where;
		return new DBhelper().count(sql, params.toArray());
	}
	public List<Map<String,Object>> query1(Product dp, String page, String rows){
		
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getProductname()!=null && dp.getProductname().trim().isEmpty() == false) {
			where += " and productname like ?";
			params.add("%" + dp.getProductname() + "%");
		}
        System.out.println(params);
		if(dp.getCid() != null &&dp.getCid() !=0) {
			where += " and cid = ?";
			params.add(dp.getCid());
		}

		if(dp.getIshot() != null &&dp.getIshot() !=0) {
			where += " and ishot = ?";
			params.add(dp.getIshot());
		}
		
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select a.*, b.cname from ttsx_product a"
				+ " join ttsx_category b on a.cid = b.id"
				+ " where 1=1"
				+ where
				+ " limit ?, ?";
		params.add(ipage);
		params.add(irows);
		return new DBhelper().query(sql, params.toArray());

	}
	public List<Product> seachid(String productname) {
		DBhelper dbh = new DBhelper();
		String sql="select * from ttsx_product where productname =? ";
		return dbh.query(sql, Product.class, productname);
		
	}//
	public List<Product> queryallnew() {
		DBhelper dbh = new DBhelper();
		String sql="select * from ttsx_product ORDER BY id desc limit 0,4 ";
		return dbh.query(sql, Product.class);
		
	}
	
}
