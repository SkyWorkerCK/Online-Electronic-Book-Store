package chenke.cqwu.test.dao;

import java.util.ArrayList;
import java.util.List;

public class Test_dao {

	public static void main(String[] args) {
		int start = 0;
		int end = 4;
		String[] columns = {"bid","bookname","price","image","stock"};
		List books = new ArrayList();
		String sql = "select * from booksck" + 
				" where bid<1000 " + 
				" order by bid asc limit " + 
				start + 
				" , " +
				end + 
				" ; ";
		BaseDao basedao = new BaseDao();
		books = basedao.query(sql, columns);
		for(int i=0;i<books.size();i++){
			System.out.println(books.get(i));
		}
	}
	
}
