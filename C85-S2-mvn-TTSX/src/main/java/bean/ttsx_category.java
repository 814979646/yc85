package bean;

import java.util.List;

public class ttsx_category {
	private Integer id;
	private String cname;
	private Integer pid;
	private List<Product> products;
	private List<ttsx_category> children;

	public List<ttsx_category> getChildren() {
		return children;
	}

	public void setChildren(List<ttsx_category> children) {
		this.children = children;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
