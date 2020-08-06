package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Order;
import bean.User;
import util.DBhelper;

public class UserDao {
	
	 /**
	  * 将用户信息创建到数据库中
	  * @param uname
	  * @param upass
	  * @param email
	  * @param head
	  */
	
	public void insert(String uname,String upass,String email,String head) {
		String sql = "insert into ttsx_user values(null,?,?,?,?,now(),?,?,?)";
		DBhelper dbh = new DBhelper();
		dbh.update(sql,uname,upass,email,"userhead/0.jpg",0,"大众会员",0);
	}
	
	public Map<String, Object> selectByLogin(String uname, String upass) {
		String sql = "select * from ttsx_user where uname=? and upass=?";
		DBhelper dbh = new DBhelper();
		List<Map<String, Object>> list = dbh.query(sql, uname, upass);
		if (list.size() == 0) {
			return null;
		} else {
			Map<String, Object> user = list.get(0);
			return user;
		}
	}
	public List<User> query(String uname) {
		String sql = "select * from ttsx_user where uname=?";
		DBhelper dbh = new DBhelper();
		return dbh.query(sql, User.class, uname);
	}
	public List<User> query1(String uid) {
		String sql = "select * from ttsx_user where id=?";
		DBhelper dbh = new DBhelper();
		return dbh.query(sql, User.class, uid);
	}
	  public List<Map<String,Object>> queryuserinfo(String uid,String id) { 
		  String sql = "SELECT\n" +
				  "	*\n" +
				  "FROM\n" +
				  "	(\n" +
				  "		SELECT\n" +
				  "			a.uname,\n" +
				  "			a.head,\n" +
				  "			b.phone,\n" +
				  "			b.address\n" +
				  "		FROM\n" +
				  "			ttsx_user a\n" +
				  "		LEFT JOIN ttsx_userinfo b ON b.uid = a.id\n" +
				  "		WHERE\n" +
				  "			uid =?\n" +
				  "		AND b.id =?\n" +
				  "	) c";
	  DBhelper dbh = new DBhelper(); 
	  return dbh.query(sql,uid,id); 
	  }
	  public void changehead(String id,String head) {
		  DBhelper dbh = new DBhelper(); 
		  String sql="update ttsx_user set head=? where id=?";
		  dbh.update(sql, head,id);
	  }
	  public void changetaojb(String id,String taojb) {
		  DBhelper dbh = new DBhelper(); 
		  String sql="update ttsx_user set taojb=? where id=?";
		  dbh.update(sql, taojb,id);
	  }
	  public List<User> queryimg(String id) {
			String sql = "select * from ttsx_user where id=?";
			DBhelper dbh = new DBhelper();
			return dbh.query(sql, User.class, id);
		}
	  public List<User> queryuname(String uname) {
			String sql = "select * from ttsx_user where uname=?";
			DBhelper dbh = new DBhelper();
			return dbh.query(sql, User.class, uname);
		}
	  public void changeupass(String upass,String uname) {
		  DBhelper dbh = new DBhelper(); 
		  String sql="update ttsx_user set upass=? where uname=?";
		  dbh.update(sql, upass,uname);
	  }
	  public void del(String id) {
	    	DBhelper dbh = new DBhelper();
	    	String sql="delete from ttsx_user where id=?";
	    	dbh.update(sql,  id);
	    }
	  public void delinfo(String id) {
	    	DBhelper dbh = new DBhelper();
	    	String sql="delete from ttsx_userinfo where uid=?";
	    	dbh.update(sql,  id);
	    }
	  public void update(String id,String uname,String email,String head) {
	    	DBhelper dbh = new DBhelper();
	    	String sql="update ttsx_user set uname=?,email=?,head=? where id=?";
	    	dbh.update(sql,  uname, email, head, id);
	    }
	  public void updateinfo(String id,String name,String phone,String address,String postal) {
	    	DBhelper dbh = new DBhelper();
	    	String sql="update ttsx_userinfo set name=?,phone=?,address=?,postal=? where uid=?";
	    	dbh.update(sql,  name, phone, address, postal,id);
	    }
	  public int count1(User dp){
			String where = "";
			List<Object> params = new ArrayList<>();
			if(dp.getId() != null && dp.getId() != 0) {
				where += " and id = ?";
				params.add(dp.getId());
			}

			if(dp.getUname()!=null && dp.getUname().trim().isEmpty() == false) {
				where += " and uname like ?";
				params.add("%" + dp.getUname() + "%");
			}
			
			if(dp.getEmail() != null && dp.getEmail().trim().isEmpty() == false) {
				where += " and email like ?";
				params.add("%" + dp.getEmail() + "%");
			}
			
			
			String sql = "select * from ttsx_user where 1=1" + where;
			return new DBhelper().count(sql, params.toArray());
		}
	  public List<Map<String, Object>> query(User dp, String page, String rows) {
			DBhelper dbh = new DBhelper();
			String where = "";
			List<Object> params = new ArrayList<>();
			if(dp.getId() != null && dp.getId() != 0) {
				where += " and a.id = ?";
				params.add(dp.getId());
			}
			
			if(dp.getUname()!=null && dp.getUname().trim().isEmpty() == false) {
				where += " and a.uname like ?";
				params.add("%" + dp.getUname() + "%");
			}
			

			if(dp.getEmail() != null && dp.getEmail().trim().isEmpty() == false) {
				where += " and a.email like ?";
				params.add("%" + dp.getEmail() + "%");
			}
			
			int ipage = Integer.parseInt(page);
			int irows = Integer.parseInt(rows);
			ipage = (ipage - 1) * 10;
			
			String sql ="SELECT\n" +
					"	a.*, b.name,b.address,\n" +
					"	b.phone,\n" +
					"	b.postal\n" +
					"FROM\n" +
					"	ttsx_user a\n" +
					"JOIN ttsx_userinfo b ON b.uid = a.id\n" +
					"WHERE\n" +
					"	1 = 1"
					+ where
					+ " limit ?, ?";
			params.add(ipage);
			params.add(irows);
			return dbh.query(sql,  params.toArray());

		}
	  public void updatatao(String taojb,String uid){
		  DBhelper dbh = new DBhelper();
			String sql="update ttsx_user set taojb=? where id=?";
			dbh.update(sql,taojb, uid);
		}
	  public void updata(String vip,String uid){
		  DBhelper dbh = new DBhelper();
			String sql="update ttsx_user set vip=? where id=?";
			dbh.update(sql,vip, uid);
		}
	  public void updatercash(String taojb,String uid){
		  DBhelper dbh = new DBhelper();
			String sql="update ttsx_user set taojb=? where id=?";
			dbh.update(sql,taojb, uid);
		}
	  public void updatacount(String count,String uid){
		  DBhelper dbh = new DBhelper();
			String sql="update ttsx_user set count=? where id=?";
			dbh.update(sql,count, uid);
		}

}
