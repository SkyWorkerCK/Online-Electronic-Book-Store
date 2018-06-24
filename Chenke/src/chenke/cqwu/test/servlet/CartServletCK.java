package chenke.cqwu.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class CartServletCK extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private BookBizCK bookbiz;
	private BookDao bookdao;
	
	@Override
	public void init() throws ServletException {		
		bookdao = new BookDaoImplCK();
		bookbiz = new BookBizImplCK();
		bookbiz.setBookdao(bookdao);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");*/
		String[] bids = req.getParameterValues("bookId");
		if(req.getParameterValues("bookId") == null){
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请选中您想要购买的书籍!');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		String[] title = req.getParameterValues("title");
		String[] price = req.getParameterValues("price");
		String[] stock = req.getParameterValues("stock");
		String[] image = req.getParameterValues("image");
		List books_in_cart;
		
		
		//获取购物车内容，没有则初始化一个列表
		books_in_cart = (List)req.getSession().getAttribute("books_in_cart");
		if(books_in_cart == null)
			books_in_cart = new ArrayList();	
		
		
		//获取复选框选中的值
		for(int i = 0; i < bids.length; i++){
			
			//测试所选的bid数组的长度
			System.out.println("bids.length = "+bids.length);
			
			boolean isNotExists = true;
			BooksCK book = new BooksCK();
			int bid = Integer.valueOf(bids[i]);
			//查看当前图书是否已经存在于购物车中
			for(int m = 0; m < books_in_cart.size(); m++){
				BooksCK exist_book = (BooksCK)books_in_cart.get(m);
				//如果当前图书已经存在于购物车，则将图书数量+1并计算当前总价
				if(exist_book.getBid() == bid){
					books_in_cart.remove(m);
					String total_price_str = exist_book.getBprice();
					books_in_cart.add(exist_book);
					exist_book.setCount( exist_book.getCount()+1 );
					isNotExists = false;
					break;
				}
			}			
			if(!isNotExists)
				continue;			
			book.setBid(bid);
			//判断当前信息是否为指定bid下的信息
			for(int j = 0; j < title.length; j++){
				String title_temp = new String(title[j].getBytes("ISO-8859-1"), "utf-8");
				if(title_temp.indexOf(bids[i] + ":") < 0)
					continue;
				if(image[j].indexOf(bids[i] + ":") < 0)
					continue;
				if(stock[j].indexOf(bids[i] + ":") < 0)
					continue;
				if(price[j].indexOf(bids[i] + ":") < 0)
					continue;
				//添加指定bid下的书籍信息
				int filter_stock = Integer.valueOf(filter(stock[j], bids[i]) );
				String filter_title =  filter(title_temp, bids[i]);
				String filter_image = filter(image[j], bids[i] );
				String filter_price = filter(price[j], bids[i]);			
				//设置图书信息
				book.setBookname(filter_title);
				book.setImage(filter_image);				
				book.setStock(filter_stock-1);
				book.setBprice(filter_price);
				book.setCount(1);
				//修改库存
				bookbiz.changeStock(bid, "-1");
				books_in_cart.add(book);
			}
		}
		req.getSession().setAttribute("books_in_cart", books_in_cart );
		req.getSession().setAttribute("books_in_cart_count", books_in_cart.size() );
		resp.sendRedirect("shopping.jsp");
	}
	
	
	//过滤客户端传回的信息
	public String filter(String s, String garbage){	
		return s.substring(garbage.length()+1, s.length());
	}

}
