package chenke.cqwu.test.biz.impl;

import java.util.List;

import chenke.cqwu.test.biz.BookBizCK;
import chenke.cqwu.test.dao.BookDao;
import chenke.cqwu.test.entity.BooksCK;

public class BookBizImplCK implements BookBizCK{
	
	private BookDao bookdao;
	public BookDao getBookdao() {
		return bookdao;
	}
	public void setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
	}

	public List<BooksCK> findAllBooks(int page_books, int page_NO) {
		int start, end;
		start = (page_NO - 1)*page_books;
		end = page_books;
		System.out.println("start =" + start);
		System.out.println("end =" + end);
		/*String sql = "select top " + page_books + " * " + 
				" from booksck " + 
				" where bid not in " + 
				" select top " + page_books*(page_NO - 1) + 
				" bid from booksck order by bid " + 
				" ) " +
				" order by bid";*/
		String sql = "select * from booksck" + 
				" where bid<1000 " + 
				" order by bid asc limit " + 
				start + 
				" , " +
				end + 
				" ; ";	
		return bookdao.query(sql);
	}

	public List<BooksCK> findBooksLikeName(String bookname) {
		String sql = "select * from booksck where bookname like '%" + bookname + "%'";
		return bookdao.query(sql);
	}

	public int count() {
		return bookdao.count();
	}

	public boolean changeStock(int bid, String change_count) {
		String sql = "update booksck set stock = stock+" + change_count + 
				" where bid = " + bid;
		return bookdao.update(sql)>0?true:false;
	}
	public boolean addBook(BooksCK book) {
		int row = bookdao.insert(book);
		return row>0?true:false;
	}

}
