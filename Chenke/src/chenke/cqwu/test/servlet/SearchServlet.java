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

public class SearchServlet extends HttpServlet{

	private BookBizCK bookbiz;
	private BookDao bookdao;
	
	@Override
	public void init() throws ServletException {		
		bookdao = new BookDaoImplCK();
		bookbiz = new BookBizImplCK();
		bookbiz.setBookdao(bookdao);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String keywords_temp = req.getParameter("keywords");
		String keywords = new String(keywords_temp.getBytes("utf-8"), "utf-8");
		System.out.println("keywords = " + keywords);
		List books = bookbiz.findBooksLikeName(keywords);
		
		req.setAttribute("books", books);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
		
	}
	
	
	
}
