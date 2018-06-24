package chenke.cqwu.test.dao;

import java.util.List;

import chenke.cqwu.test.entity.BooksCK;

public interface BookDao {
	public List query(String sql);
	public int insert(BooksCK book);
	public int count();
	public int update(String sql);
}
