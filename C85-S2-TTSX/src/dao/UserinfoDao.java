package dao;

import java.util.List;
import java.util.Map;

import bean.Userinfo;
import util.DBhelper;

public class UserinfoDao {
	DBhelper dbh=new DBhelper();
	public void insert(String name,String address,String postal,String phone,String uid) {
		//DBhelper dbh=new DBhelper();
		String sql="insert into ttsx_userinfo values(null,?,?,?,?,?,now())";
		dbh.update(sql,  name, address, postal, phone, uid);
	}
	public List<Userinfo> query(String uid){
		String sql="select * from ttsx_userinfo where uid=?";
		return dbh.query(sql, Userinfo.class, uid);
	}
	public List<Userinfo> queryid(String id){
		String sql="select * from ttsx_userinfo where id=?";
		return dbh.query(sql, Userinfo.class, id);
	}
	public void del(String id){
		String sql="delete from ttsx_userinfo where id=?";
		dbh.update(sql, id);
	}
	public void updata(String name,String address,String postal,String phone,String id){
		String sql="update ttsx_userinfo set name=?,address=?,postal=?,phone=? where id=?";
		dbh.update(sql,  name, address, postal, phone, id);
	}
	public List<Userinfo> queryaddress(String id){
		String sql="select address from ttsx_userinfo where uid=?";
		return dbh.query(sql, Userinfo.class, id);
	}
	
	

}
