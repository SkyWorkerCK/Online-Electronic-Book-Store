package chenke.cqwu.test.dao;

import java.util.List;

import chenke.cqwu.test.entity.OrderCK;

public interface OrderDaoCK {
	
	public List query(String sql);
	public int insert(OrderCK order);
	public int count();
	public List queryByUsername(String sql);
	
}
