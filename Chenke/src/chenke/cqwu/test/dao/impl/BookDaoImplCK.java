package chenke.cqwu.test.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chenke.cqwu.test.dao.BaseDao;
import chenke.cqwu.test.dao.BookDao;
import chenke.cqwu.test.entity.BooksCK;

public class BookDaoImplCK extends BaseDao implements BookDao{

	public List query(String sql) {
		String[] columns = {"bid","bookname","bprice","image","stock"};
		return query(sql,columns);
	}

	public int insert(BooksCK book) {
		String table = "booksck";
		List list = new ArrayList();
		list.add(book.getBid());
		list.add(book.getBookname());
		list.add(book.getBprice());
		list.add(book.getImage());
		list.add(book.getStock());
		return insert(table, list);
	}

	public int count() {
		String sql = "select count(*) from booksck";
		openconnection();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int update(String sql) {
		return deleteOrUpdate(sql);
	}
	
}
