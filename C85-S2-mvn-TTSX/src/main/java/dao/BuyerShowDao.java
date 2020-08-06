package dao;

import java.util.List;

import bean.BuyerShow;
import util.DBhelper;

public class BuyerShowDao {
	
	DBhelper dbh=new DBhelper();
    public void insert(String pid,String name,String content,String image1,String image2,String image3,String images) {
    	String sql="insert into ttsx_buyershow values(null,?,?,?,?,?,?,now(),?,?)";
    	dbh.update(sql,  pid, name, content, image1, image2,image3,0,images);
    }
    public List<BuyerShow> query1(String pid){
		String sql="select * from ttsx_buyershow where pid=? ";
		return dbh.query(sql, BuyerShow.class, pid);
	}
    public int countPages(String pid) {
		DBhelper dbh = new DBhelper();
		// System.out.println(cid);
		String sql = "select * from ttsx_buyershow where pid=?";
		int count = dbh.count(sql, pid);
		// System.out.println(count);
		if (count % 8 == 0) {
			return count / 8;
		} else {
			return count / 8 + 1;
		}

	}
	public List<BuyerShow> query(String pid,int page){
		int begin = (page - 1) * 8;
		String sql="select * from ttsx_buyershow where pid=? ORDER BY zan desc limit ?,?";
		return dbh.query(sql, BuyerShow.class, pid, begin, 8);
	}
	public void updatezan(String zan,String id){
		String sql="update ttsx_buyershow set zan=? where id=?";
		 dbh.update(sql,  zan, id);
	}
	 public void changehead(String images,String name) {
		  DBhelper dbh = new DBhelper(); 
		  String sql="update ttsx_buyershow set images=? where name=?";
		  dbh.update(sql, images,name);
	  }

}
