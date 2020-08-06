package com.yc.favorite.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.TagFavorite;

public interface TagFavoriteMapper {

	@Insert("insert into tagfavorite values(#{tid},#{fid})")
	int insert(TagFavorite f);
	
}
