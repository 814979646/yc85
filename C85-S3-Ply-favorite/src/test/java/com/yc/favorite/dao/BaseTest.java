package com.yc.favorite.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.favorite.FavoriteBiz;
import com.yc.favorite.bean.Favorite;
import com.yc.favorite.util.MybatisHelper;

public class BaseTest {

	@Test
	public void test1() {
		SqlSession session = MybatisHelper.openSqlSession();
		FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
		TagFavoriteMapper tm = session.getMapper(TagFavoriteMapper.class);
		TagMapper ftm = session.getMapper(TagMapper.class);

		Favorite f = new Favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("百家网站");
		f.setFtags("购物,生活");

		fm.insert(f);
		session.commit();
	}

	@Test
	public void test2() {
		
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("百家网站");
		f.setFtags("购物,生活");
		fb.addFavorite(f);
	}

	@Test
	public void test3() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("网易");
		f.setFurl("163.com");
		f.setFdesc("好网站");
		f.setFtags("门户,军事,生活");
		fb.addFavorite(f);

	}
}
