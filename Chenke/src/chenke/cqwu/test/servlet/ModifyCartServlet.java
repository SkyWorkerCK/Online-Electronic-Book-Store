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
	 * �Ƴ�����
	 */
	private void remove(HttpServletRequest req, HttpServletResponse resp){
		String bid_str = req.getParameter("bid");
		int bid = Integer.valueOf(bid_str);
		//��ȡ���ﳵ
		List books_in_cart = (List)req.getSession().getAttribute("books_in_cart");
		//���ҹ��ﳵ�б��޸Ĺ���ͼ�鲢�Ƴ���ͼ��
		for(int i = 0; i < books_in_cart.size(); i++ ){
			BooksCK book = (BooksCK)books_in_cart.get(i);
			if(bid == book.getBid()){
				//�Ƴ�ͼ��
				books_in_cart.remove(i);
				//�޸Ŀ��
				bookbiz.changeStock(bid, "1");
				break;
			}
		}
		req.getSession().setAttribute("books_in_cart_count", books_in_cart.size() );
	}
	/*
	 * �޸Ĳ���
	 */
	private void modify(HttpServletRequest req, HttpServletResponse resp){
		//��ȡͼ��id���޸ĺ������
		String bid_str = req.getParameter("bid");
		String count_str = req.getParameter("count");
		int bid = Integer.valueOf(bid_str);
		int count = Integer.valueOf(count_str);
		//��ȡ���ﳵ
		List books_in_cart = (List)req.getSession().getAttribute("books_in_cart");
		//���ҹ��ﳵ�б��޸Ĺ���ͼ�鲢�޸���Ӧ��Ϣ
		for(int i = 0; i < books_in_cart.size(); i++ ){
			BooksCK book = (BooksCK)books_in_cart.get(i);
			if(bid == book.getBid()){
				//��ȡ�޸�ǰ��ͼ������
				int old_count = book.getCount();
				//���õ�ǰͼ���������
				book.setCount(count);
				//�޸Ŀ��
				bookbiz.changeStock(bid, new String("" + (old_count - count)));
				break;
			}
		}
	}
	
}
