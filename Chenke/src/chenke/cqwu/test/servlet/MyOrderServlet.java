package chenke.cqwu.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chenke.cqwu.test.biz.OrderBizCK;
import chenke.cqwu.test.biz.impl.OrderBizImplCK;
import chenke.cqwu.test.dao.OrderDaoCK;
import chenke.cqwu.test.dao.impl.OrderDaoImplCK;

public class MyOrderServlet extends HttpServlet{

	private OrderBizCK orderbiz = null;
	private OrderDaoCK orderdao = null;
	
	@Override
	public void init() throws ServletException {
		orderdao = new OrderDaoImplCK();
		orderbiz = new OrderBizImplCK();
		orderbiz.setOrderdao(orderdao);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = (String)req.getSession().getAttribute("loginuser");
		List orders = orderbiz.findOrderByUser(username, 0, 0);
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
	}
	
}
