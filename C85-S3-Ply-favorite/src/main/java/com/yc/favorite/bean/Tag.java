package com.yc.favorite.bean;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tid;
	private String tname;
	private Integer fcount;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getFcount() {
		return fcount;
	}

	public void setFcount(Integer fcount) {
		this.fcount = fcount;
	}

	@Override
	public String toString() {
		return "Tag [tid=" + tid + ", tname=" + tname + ", fcount=" + fcount + "]";
	}

}
