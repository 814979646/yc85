package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Order;
import bean.Porder;
import bean.Status;
import util.DBhelper;

public class OrderDao {
	DBhelper dbh = new DBhelper();
	private String num;

	public void insert(String status, String orderprices, String uid, String status1) {
		double ordernum1 = Math.random() * 10000000;
		String ordernum = String.valueOf(ordernum1).substring(0, 8);
		num = ordernum;
		String sql = "insert into ttsx_order values(null,now(),?,?,?,?,?,null,null,null)";
		dbh.update(sql, ordernum, "未支付", orderprices, uid, "待付款");
	}
	
	public void updateaddress(String address, String phone, String name,String id) {
		
		String sql = "update ttsx_order set address=?,phone=?,name=? where id=?";
		dbh.update(sql,  address,  phone,  name, id);
	}

	public List<Order> query() {
		String ordernum = num;
		String sql = "select * from ttsx_order where ordernum=?";
		return dbh.query(sql, Order.class, ordernum);

	}

	public void update(String oid, String address, String phone, String name,String orderprices) {
		String sql = "update ttsx_order set status=?,status1=?,address=?,phone=?,name=?,orderprices=? where id=?";
		dbh.update(sql, "未支付", "待付款", address, phone, name,orderprices, oid);

	}

	public List<Map<String, Object>> queryid(String uid) {
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	ttsx_order a\n" + "LEFT JOIN ttsx_porder b ON b.oid = a.id\n"
				+ "WHERE\n" + "	a.uid = ?";
		return dbh.query(sql, uid);
	}

	public List<Porder> queryoid(String oid) {
		String sql = "select * from ttsx_porder where oid=?";
		return dbh.query(sql, Porder.class, oid);

	}

	public List<Order> query(String uid) {
		String sql = "select * from ttsx_order where uid=?";
		return dbh.query(sql, Order.class, uid);

	}

	public List<Map<String, Object>> query1(String uid, int page) {
		int begin = (page - 1) * 5;
		String sql = "select * from ttsx_order where uid=? ORDER BY id desc limit ?,? ";
		return dbh.query(sql, uid, begin, 5);

	}

	public int countPages(String uid) {
		DBhelper dbh = new DBhelper();
		// System.out.println(cid);
		String sql = "select * from ttsx_order where uid=?";
		int count = dbh.count(sql, uid);
		// System.out.println(count);
		if (count % 5 == 0) {
			return count / 5;
		} else {
			return count / 5 + 1;
		}

	}

	
	public int count1(Order dp){
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getId() != null && dp.getId() != 0) {
			where += " and id = ?";
			params.add(dp.getId());
		}

		if(dp.getName()!=null && dp.getName().trim().isEmpty() == false) {
			where += " and name like ?";
			params.add("%" + dp.getName() + "%");
		}
		
		if(dp.getStatus() != null && dp.getStatus().trim().isEmpty() == false) {
			where += " and status like ?";
			params.add("%" + dp.getStatus() + "%");
		}
		
		
		String sql = "select * from ttsx_order where 1=1" + where;
		return new DBhelper().count(sql, params.toArray());
	}
	
	
	
	public List<Map<String, Object>> queryorder(Order dp, String page, String rows) {
		DBhelper dbh = new DBhelper();
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getId() != null && dp.getId() != 0) {
			where += " and a.id = ?";
			params.add(dp.getId());
		}
		
		if(dp.getName()!=null && dp.getName().trim().isEmpty() == false) {
			where += " and a.name like ?";
			params.add("%" + dp.getName() + "%");
		}
		

		if(dp.getStatus() != null && dp.getStatus().trim().isEmpty() == false) {
			where += " and a.status like ?";
			params.add("%" + dp.getStatus() + "%");
		}
		
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		
		String sql ="SELECT\n" +
				"	a.*, b.pname,\n" +
				"	b.images,\n" +
				"	b.price\n" +
				"FROM\n" +
				"	ttsx_order a\n" +
				"JOIN ttsx_porder b ON a.id = b.oid\n" +
				"WHERE\n" +
				"	1 = 1"
				+ where
				+ " ORDER BY id desc limit ?, ?";
		params.add(ipage);
		params.add(irows);
		return dbh.query(sql,  params.toArray());

	}
	
	
	
	
	
	
    public void save(String ordernum,String name,String address,String phone ,String status,String status1,String orderprices,String id) {
    	DBhelper dbh = new DBhelper();
    	String sql="update ttsx_order set ordernum=?,name=?,address=?,phone=?,status=?, status1=?,orderprices=? where id=?";
    	dbh.update(sql, ordernum, name, address, phone, status, status1,orderprices,  id);
    }
    public void del(String id) {
    	DBhelper dbh = new DBhelper();
    	String sql="delete from ttsx_order where id=?";
    	dbh.update(sql,  id);
    }
    public List<Order> querydata(String id) {
    	DBhelper dbh = new DBhelper();
    	String sql="select * from ttsx_order where id=?";
		return dbh.query(sql, Order.class, id);
    }
    public void updateorder(String oid, String status, String status1) {
		String sql = "update ttsx_order set status=?,status1=? where id=?";
		dbh.update(sql, status,status1, oid);

	}
    public void updatesaveorder(String oid, String status, String status1) {
		String sql = "update ttsx_order set status=?,status1=? where id=?";
		dbh.update(sql, status,status1, oid);

	}
    public List<Status> querystatus() {
		String sql = "select status from ttsx_status ";
		return dbh.query(sql, Status.class);
		

	}
    public List<Status> querystatus1() {
		String sql = "select status1 from ttsx_status ";
		return dbh.query(sql, Status.class);
		

	}
    public List<Order> queryprices(String oid) {
		String sql = "select * from ttsx_order where id=?";
		return dbh.query(sql, Order.class, oid);

	}
    public List<Order> queryordernum(String ordernum) {
		String sql = "select * from ttsx_order where ordernum=?";
		return  dbh.query(sql, Order.class, ordernum);

	}
    public void updatecash(String ordernum, String status1) {
		String sql = "update ttsx_order set status1=? where ordernum=?";
		dbh.update(sql,status1, ordernum);

	}
    
}
