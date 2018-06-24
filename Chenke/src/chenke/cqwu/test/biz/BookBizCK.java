package chenke.cqwu.test.biz;

import java.util.List;

import chenke.cqwu.test.dao.BookDao;
import chenke.cqwu.test.entity.BooksCK;

public interface BookBizCK {
	public List<BooksCK> findAllBooks(int page_books, int page_NO);
	public List<BooksCK> findBooksLikeName(String bookname);
	public boolean addBook(BooksCK book);
	public BookDao getBookdao();
	public void setBookdao(BookDao bookdao);
	public int count();
	public boolean changeStock(int bid, String change_count);
}
