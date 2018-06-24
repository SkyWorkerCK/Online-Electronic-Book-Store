package chenke.cqwu.test.biz.impl;

import java.util.List;
import java.util.Map;

import chenke.cqwu.test.biz.OrderBizCK;
import chenke.cqwu.test.dao.OrderDaoCK;
import chenke.cqwu.test.entity.OrderCK;

public class OrderBizImplCK implements OrderBizCK{

	private OrderDaoCK orderdao = null;
	
	public OrderDaoCK getOrderdao() {
		return orderdao;
	}
	public void setOrderdao(OrderDaoCK orderdao) {
		this.orderdao = orderdao;
	}
	
	
	public boolean newOrder(OrderCK order) {
		int row = orderdao.insert(order);
		return row>0?true:false;
	}
	public List findOrderByUser(String username, int page_book, int page_no) {
		String sql = "select * " +
				"from orders bo, items bi , booksck bb " +
				"where bo.oid = bi.oid and bb.bid = bi.bid order by bi.oid";
		return orderdao.query(sql);
	}
	public String getCurrentOid(String username) {
		String sql = "select * from orders where username = '" + username + "' order by oid desc";
		List orders = orderdao.queryByUsername(sql);
		Map order = (Map)orders.get(0);
		return new String("" + order.get("oid"));
	}
	public int count() {
		return orderdao.count();
	}
}
