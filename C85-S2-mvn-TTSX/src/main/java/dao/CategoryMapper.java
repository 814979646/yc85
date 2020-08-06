package dao;

import java.util.List;

import bean.ttsx_category;

public interface CategoryMapper {

	List<ttsx_category> selectAll();
	
	int insert(ttsx_category dc);
	
	int update(ttsx_category dc);
	
	int delete(int id);
	
	ttsx_category selectById(int id);
}
