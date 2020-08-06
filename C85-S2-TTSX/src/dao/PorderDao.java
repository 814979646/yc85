package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Porder;
import util.DBhelper;

public class PorderDao {

	DBhelper dbh=new DBhelper();
	public void insert(String oid,String pname,String price,String spesc,String count,String images,String prices,String uid) {
		String sql="insert into ttsx_porder values(null,?,?,?,?,?,now(),?,?,?)";
		dbh.update(sql,  oid, pname, price, spesc, count, images, uid, prices);
	}
	public List<Porder> query(String oid){
		String sql="select * from ttsx_porder where oid=?";
		return dbh.query(sql, Porder.class, oid);
	}
	public int count(String oid){
		String sql="select * from ttsx_porder where oid=?";
		return dbh.count(sql, oid);
	}
	public List<Porder> queryname(String oid){
		String sql="select * from ttsx_porder where oid=?";
		return dbh.query(sql, Porder.class, oid);
	}
	public void del(String oid){
		String sql="delete  from ttsx_porder where oid=?";
		dbh.update(sql, oid);
	}
	public List<Porder> queryporder(String oid, String pname, String prices) {
		DBhelper dbh = new DBhelper();
		String sql = "select * from ttsx_porder where 1=1";
		List<Object> params = new ArrayList<>();
		if (oid != null && oid.trim().isEmpty() == false) {
			sql += " and oid=?";
			params.add(oid);
		}
		if (pname != null && pname.trim().isEmpty() == false) {
			sql += " and pname like concat('%',?,'%')";
			params.add(pname);
		}

		if (prices != null && prices.trim().isEmpty() == false) {
			sql +=" and prices=?";
			params.add(prices);
		}
		return dbh.query(sql, Porder.class, params.toArray());

	}
	public int count1(String uid){
		String sql="select * from ttsx_porder where uid=?";
		return dbh.count(sql, uid);
	}
	
}
