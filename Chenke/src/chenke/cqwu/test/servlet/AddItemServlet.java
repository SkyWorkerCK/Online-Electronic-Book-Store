package chenke.cqwu.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chenke.cqwu.test.biz.ItemBizCK;
import chenke.cqwu.test.biz.OrderBizCK;
import chenke.cqwu.test.biz.impl.ItemBizImplCK;
import chenke.cqwu.test.biz.impl.OrderBizImplCK;
import chenke.cqwu.test.dao.ItemDaoCK;
import chenke.cqwu.test.dao.OrderDaoCK;
import chenke.cqwu.test.dao.impl.ItemDaoImplCK;
import chenke.cqwu.test.dao.impl.OrderDaoImplCK;
import chenke.cqwu.test.entity.ItemCK;
import chenke.cqwu.test.entity.OrderCK;

public class AddItemServlet extends HttpServlet{

	private ItemBizCK itembiz = null;
	private ItemDaoCK itemdao = null;
	private OrderBizCK orderbiz = null;
	private OrderDaoCK orderdao = null;

	@Override
	public void init() throws ServletException {		
		itemdao = new ItemDaoImplCK();
		itembiz = new ItemBizImplCK();
		itembiz.setItemdao(itemdao);
		
		orderdao = new OrderDaoImplCK();
		orderbiz = new OrderBizImplCK();
		orderbiz.setOrderdao(orderdao);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取订单信息
		String username = (String)req.getSession().getAttribute("loginuser");
		OrderCK order = new OrderCK();
		order.setUsername(username);
		//生成订单
		if(!orderbiz.newOrder(order)){
			System.out.println("添加失败");
			return;
		}
		//生成订单项
		String count_str = req.getParameter("count");
		Integer count = Integer.valueOf(count_str);		
		String oid_str = orderbiz.getCurrentOid(username);			
		for(int i = 1; i < count; i++){
			String bid_str = req.getParameter("hidden_bid_" + i);
			String book_count = req.getParameter("nums_" + i);
			String book_total_price = req.getParameter("hidden_book_total_price_" + i);
			String hidden_total_price = req.getParameter("hidden_total_price");
			ItemCK item = new ItemCK();
			item.setOid(Integer.valueOf(oid_str));
			item.setBid(Integer.valueOf(bid_str));
			item.setCount(Integer.valueOf(book_count));
			item.setIprice(book_total_price);
			item.setState(0);
			item.setTotal_price(hidden_total_price);
			itembiz.addItemToOrder(item);
		}
		req.getSession().removeAttribute("books_in_cart");
		req.getSession().removeAttribute("books_in_cart_count");
		resp.sendRedirect("order");
	}
	//过滤客户端传回的信息
	public String filter(String s, String garbage){
		
		return s.substring(garbage.length()+1, s.length());
	}
	
}
