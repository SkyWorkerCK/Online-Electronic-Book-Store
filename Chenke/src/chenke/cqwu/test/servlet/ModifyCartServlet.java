package chenke.cqwu.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chenke.cqwu.test.biz.BookBizCK;
import chenke.cqwu.test.biz.impl.BookBizImplCK;
import chenke.cqwu.test.dao.BookDao;
import chenke.cqwu.test.dao.impl.BookDaoImplCK;
import chenke.cqwu.test.entity.BooksCK;

public class ModifyCartServlet extends HttpServlet{

	private BookBizCK bookbiz = null;
	private BookDao bookdao = null;
	
	@Override
	public void init() throws ServletException {		
		bookdao = new BookDaoImplCK();
		bookbiz = new BookBizImplCK();
		bookbiz.setBookdao(bookdao);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action.equals("remove"))
			remove(req, resp);
		else if(action.equals("modify"))
			modify(req, resp);	
	}
	/*
	 * 移除操作
	 */
	private void remove(HttpServletRequest req, HttpServletResponse resp){
		String bid_str = req.getParameter("bid");
		int bid = Integer.valueOf(bid_str);
		//获取购物车
		List books_in_cart = (List)req.getSession().getAttribute("books_in_cart");
		//查找购物车中被修改过的图书并移除该图书
		for(int i = 0; i < books_in_cart.size(); i++ ){
			BooksCK book = (BooksCK)books_in_cart.get(i);
			if(bid == book.getBid()){
				//移除图书
				books_in_cart.remove(i);
				//修改库存
				bookbiz.changeStock(bid, "1");
				break;
			}
		}
		req.getSession().setAttribute("books_in_cart_count", books_in_cart.size() );
	}
	/*
	 * 修改操作
	 */
	private void modify(HttpServletRequest req, HttpServletResponse resp){
		//获取图书id和修改后的数量
		String bid_str = req.getParameter("bid");
		String count_str = req.getParameter("count");
		int bid = Integer.valueOf(bid_str);
		int count = Integer.valueOf(count_str);
		//获取购物车
		List books_in_cart = (List)req.getSession().getAttribute("books_in_cart");
		//查找购物车中被修改过的图书并修改相应信息
		for(int i = 0; i < books_in_cart.size(); i++ ){
			BooksCK book = (BooksCK)books_in_cart.get(i);
			if(bid == book.getBid()){
				//获取修改前的图书数量
				int old_count = book.getCount();
				//设置当前图书的新数量
				book.setCount(count);
				//修改库存
				bookbiz.changeStock(bid, new String("" + (old_count - count)));
				break;
			}
		}
	}
	
}
