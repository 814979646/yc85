package dao;

import java.util.List;

import util.DBhelper;

public class CategoryDao {
	
	public List<?> query() {
		return new DBhelper().query("select * from ttsx_category");

	}

}
