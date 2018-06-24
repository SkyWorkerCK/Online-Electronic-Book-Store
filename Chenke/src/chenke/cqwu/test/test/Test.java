package chenke.cqwu.test.test;

import java.util.ArrayList;
import java.util.List;

import chenke.cqwu.test.dao.BaseDao;
import chenke.cqwu.test.dao.UserDaoCK;
import chenke.cqwu.test.dao.impl.UserDaoImplCK;
import chenke.cqwu.test.entity.UserInfoCK;

public class Test {
	
	public static void main(String[] args) {
		/*UserDaoCK basedao = new UserDaoImplCK();
		List<String> list = new ArrayList<String>();
		UserInfoCK userinfock = new UserInfoCK();
		userinfock.setUsernameCK("chenke");
		userinfock.setPasswordCK("123456");
		userinfock.setEmailCK("132456");
		basedao.insert(userinfock);*/
		int start = 0;
		int end = 3;
		String sql = "select * from booksck" + 
				" where bid<1000 " + 
				" order by bid asc limit " + 
				start + 
				" , " +
				end + 
				" ; ";
		BaseDao basedao = new BaseDao();
	}
	
}
