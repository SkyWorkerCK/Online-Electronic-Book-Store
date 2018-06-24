package chenke.cqwu.test.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chenke.cqwu.test.dao.BaseDao;
import chenke.cqwu.test.dao.OrderDaoCK;
import chenke.cqwu.test.entity.OrderCK;

public class OrderDaoImplCK extends BaseDao implements OrderDaoCK {

	public List query(String sql) {
		String[] columns = {"oid", "username", "bid", "createdate", 
				"count", "iprice", "state", "bprice", "image", "bookname", "total_price"};
		return query(sql, columns);
	}

	public int insert(OrderCK order) {
		String table = "orders";
		List list = new ArrayList();
		list.add(order.getOid());
		list.add(order.getUsername());
		return insert(table, list);
	}

	public int count() {
		String sql = "select count(*) from orders";
		openconnection();
		int i=0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return i;
	}

	public List queryByUsername(String sql) {
		String[] columns = {"oid", "username"};
		return query(sql, columns);
	}

}
