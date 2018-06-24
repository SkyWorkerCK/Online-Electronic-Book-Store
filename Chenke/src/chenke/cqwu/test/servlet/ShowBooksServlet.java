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

public class ShowBooksServlet extends HttpServlet {

	private BookBizCK bookbiz;
	private BookDao bookdao;

	public void init() throws ServletException {
		bookbiz = new BookBizImplCK();
		bookdao = new BookDaoImplCK();
		bookbiz.setBookdao(bookdao);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String NO_str = req.getParameter("current_books_NO");
		int NO = NO_str == null ? 1 : Integer.valueOf(NO_str);
		int total_books = bookbiz.count();
		int book_num = 5; // book_num = PageTools.book_num
		int total_page = total_books / book_num + 1;

		System.out.println("NO = " + NO);
		System.out.println("total_books = " + total_books);
		System.out.println("book_num = " + book_num);
		System.out.println("total_page = " + total_page);

		// 获取全部图书信息
		List books = bookbiz.findAllBooks(5, NO);
		req.setAttribute("books", books);
		req.setAttribute("current_books_NO", NO);
		req.setAttribute("total_books_page", total_page);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
}
