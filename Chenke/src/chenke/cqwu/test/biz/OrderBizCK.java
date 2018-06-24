package chenke.cqwu.test.biz;

import java.util.List;

import chenke.cqwu.test.dao.OrderDaoCK;
import chenke.cqwu.test.entity.OrderCK;

public interface OrderBizCK {
	
	public boolean newOrder(OrderCK order);
	public List findOrderByUser(String username, int page_book, int page_no);
	public OrderDaoCK getOrderdao();
	public void setOrderdao(OrderDaoCK orderdao);
	public String getCurrentOid(String username);
	public int count();
}
