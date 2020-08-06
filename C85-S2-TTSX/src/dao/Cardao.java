package dao;

import java.util.List;
import java.util.Map;

import bean.Car;
import util.DBhelper;

public class Cardao {
	DBhelper dbh = new DBhelper();

	public void insert(String porductname, String specs, String price1, Integer count, String prices, String comunit,
			Integer uid, String images) {
		String sql = "insert into ttsx_car values(null,?,?,?,?,?,now(),?,?,?,?)";
		dbh.update(sql, porductname, specs, price1, count, prices, null, comunit, uid, images);
	}

	public List<Car> query(Integer uid) {
		String sql = "select * from ttsx_car where uid=?";
		return dbh.query(sql, Car.class, uid);

	}

	public void update(Integer id, Integer count, String prices) {
		String sql = "update ttsx_car set count=?,prices=? where id=?";
		dbh.update(sql, count, prices, id);

	}

	public void del(String id) {
		String sql = "delete from ttsx_car where id=?";
		dbh.update(sql, id);
	}

	public int count(String uid) {
		String sql = "select * from ttsx_car where uid=?";
		return dbh.count(sql, uid);
	}

	public void delcar(String id) {
		String sql = "delete from ttsx_car where id=?";
		dbh.update(sql, id);

	}

	public List<Car> queryprices(String id) {
		String sql = "select * from ttsx_car where id=?";
		return dbh.query(sql, Car.class, id);

	}

}
