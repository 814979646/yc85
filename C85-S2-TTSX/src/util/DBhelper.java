package util;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.sql.Date;

import util.IOhelp;

public class DBhelper {
	private Connection conn;
	private boolean isAutoCommit = true;
	/**
	 * static块不能抛出编译期异常
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			RuntimeException re = new RuntimeException("数据库驱动加载失败！", e);// 编译期异常转型成运行期异常
			throw re;
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public Connection openConnection() {
		String url = "jdbc:mysql://127.0.0.1/c85-s2-ttsx?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";//caohaolin
		String password = "a";//Caohaolin0523!
		try {
			if (isAutoCommit) {
				return DriverManager.getConnection(url, user, password);
			} else {
				if (conn == null) {
					// 禁止自动提交
					conn = DriverManager.getConnection(url, user, password);
					conn.setAutoCommit(isAutoCommit);
				}
				return conn;
			}
		} catch (SQLException e) {
			throw new RuntimeException("获取数据库连接失败！", e);
		}
	}

	/**
	 * 使用 isAutoCommit 决定是否自动提交
	 * 
	 * 如果是自动提交, 则意味着每次执行 update 方法都要获取新的连接, 在执行之后关闭连接 否则, 不关闭连接
	 * 
	 * @param isAutoCommit 自动提交 true
	 */

	public DBhelper(boolean isAutoCommit) {
		this.isAutoCommit = isAutoCommit;
		if (isAutoCommit == false) {
			conn = openConnection();
		}
	}

	public DBhelper() {
		/**
		 * JDBC 连接默认是自动提交, 也就是每次执行完增删改都会自动提交
		 */
		// 构造方法打开连接
		conn = openConnection();
	}

	// 关闭连接
	public void closeConnection() {
		IOhelp.close(conn);
	}

	// 获取连接
	public Connection getConn() {
		return conn;
	}

	/**
	 * 
	 * @param sql    执行sql语句
	 * @param params 可变参数组
	 * @return
	 */
	public int update(String sql, Object... params) {

		try {
			// 每次都会通过open方法获取连接
			conn = openConnection();
			 System.out.println("SQL:" + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("参数" + Arrays.toString(params));
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("sql语句执行失败！", e);
		} finally {
			if (isAutoCommit == true) {
				IOhelp.close(conn);
			}
		}

	}

	/**
	 * 执行查询
	 * 
	 * @param args
	 */
	public List<Map<String, Object>> query(String sql, Object... params) {

		try {
			conn = openConnection();
			// System.out.println("SQL:" + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			// System.out.println("参数" + Arrays.toString(params));
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			// 获取结果集元数据，元（Meta）数据(data)：描述数据的数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 创建返回结果对象
			List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<String, Object>();

				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					// 得到列名
					String mapkey = rsmd.getColumnName(i + 1);
					// 得到列值
					Object mapvalues = rs.getObject(mapkey);
					// 放入map中
					row.put(mapkey, mapvalues);
				}
				// 将map放入List中
				ret.add(row);
			}
			return ret;
		} catch (SQLException e) {
			throw new RuntimeException("sql语句执行失败！", e);
		} finally {
			if (isAutoCommit == true) {
				IOhelp.close(conn);
			}
		}

	}

	/**
	 * 返回值是可变的实体类 所有的集合===>>泛型类
	 * 
	 * @param <E>    方法转为泛型
	 * @param sql
	 * @param cls
	 * @param params
	 * @return
	 */
	public <E> List<E> query(String sql, Class<E> cls, Object... params) {

		try {
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			// 获取结果集元数据，元（Meta）数据(data)：描述数据的数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 创建返回结果对象
			List<E> ret = new ArrayList<>();
			while (rs.next()) {
				// 创建实体类对象（通过反射创建实体类对象）
				E e;
				try {
					e = cls.newInstance();// 创建一个对象
				} catch (Exception e1) {
					throw new RuntimeException(e1);
				}

				// 通过反射进行属性值的设置
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					try {
						// 通过当前列找对应属性
						String columnName = rsmd.getColumnName(i + 1);
						columnName = columnName.toLowerCase();// 转小写
						// 获取当前类的属性 包括私有
						Field field = cls.getDeclaredField(columnName);
						// 获取当前列的值
						/**
						 * ID===>JDBC数据类型 BigDecimal 大实数 表示任意大小的数字 实体类类型Long
						 * 
						 */
						Object value = rs.getObject(i + 1);
						// 要转换的值
						Object destvalue = null;
						//
						if(value==null) {continue;}

						if (field.getType().equals(Long.class) && value != null) {
							destvalue = rs.getLong(i+1);
						} else if (field.getType().equals(Double.class) && value != null) {
							destvalue = rs.getDouble(i+1);
						} else if (field.getType().equals(Integer.class) && value != null) {
							destvalue = rs.getInt(i+1);
						} else if (field.getType().equals(Boolean.class) && value != null) {
							destvalue =  rs.getBoolean(i+1);
						} else if (field.getType().equals(Byte.class) && value != null) {
							destvalue = rs.getByte(i+1);
						} else if (field.getType().equals(Character.class) && value != null) {
							destvalue = rs.getCharacterStream(i+1);
						} else if (field.getType().equals(Short.class) && value != null) {
							destvalue =rs.getShort(i+1);
						}else if (field.getType().equals(Timestamp.class) && value != null) {
							destvalue = rs.getTimestamp(i+1);
						}else if (field.getType().equals(Date.class) && value != null) {
							destvalue =rs.getDate(i+1);
						} else {
							destvalue = rs.getObject(i+1);
						}
						// 设置强制访问私有属性
						field.setAccessible(true);
						// 将值设置进属性中
						field.set(e, destvalue);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

				ret.add(e);
			}
			return ret;
		} catch (SQLException e) {
			throw new RuntimeException("sql语句执行失败！", e);
		} finally {
			if (isAutoCommit == true) {
				IOhelp.close(conn);
			}
		}

	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param pagenumber
	 * @param pagesize
	 * @param params
	 * @return
	 */

	public List<Map<String, Object>> querypage(String sql, int pagenumber, int pagesize, Object... params) {
		int begin = (pagenumber - 1) * pagesize + 1;
		int end = pagenumber * pagesize;
		Object newparams[] = new Object[params.length + 2];
		System.arraycopy(params, 0, newparams, 0, params.length);
		newparams[newparams.length - 2] = end;
		newparams[newparams.length - 1] = begin;
		sql = "select * from (select rownum as rn,a.* from (" + sql + ") a where rownum<=?) where rn>=?";
		return query(sql, newparams);

	}

	/**
	 * 作业: 请返回该语句结果集的行数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int count(String sql, Object... params) {
		//mysql  子查询必须有别名
		sql = "select count(*) as cnt from (" + sql + ") a";
		//mysql的字段名不会转换成大写    oracle才行
		Object cnt = query(sql, params).get(0).get("cnt");
		int ret = Integer.valueOf("" + cnt);
		return ret;

	}

	/**
	 * 作业: 返回结果集中, 第一行,第一列的值 select count(*) from emp;
	 * 
	 * @return
	 */
	public Object getValue(String sql, Object... params) {
		List<Map<String, Object>> list = query(sql, params);
		Map<String, Object> row = list.get(0);
		for (Entry<String, Object> entry : row.entrySet()) {
			return entry.getValue();
		}
		return null;
	}

	public static void main(String[] args) {
		DBhelper dbh = new DBhelper();
		// dbh.update("insert into dept values(?,?,?)", 60, "人力部", "衡阳");
		// List<Map<String, Object>> list = dbh.query("select * from (select rownum as
		// rn,a.* from emp a where rownum<=?) where rn>=?", 5, 1);
		/*
		 * for (Map<String, Object> map : list) { System.out.println(map); }
		 */
		/*
		 * list=dbh.querypage("select * from emp ", 2, 5); for (Map<String, Object> map
		 * : list) { System.out.println(map); }
		 */
		// System.out.println(dbh.count("select * from emp"));
		// System.out.println(dbh.query("select * from SALGRADE"));
	}

}
