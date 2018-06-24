package chenke.cqwu.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import chenke.cqwu.test.dao.BaseDao;
import chenke.cqwu.test.dao.UserDaoCK;
import chenke.cqwu.test.entity.UserInfoCK;

public class UserDaoImplCK extends BaseDao implements UserDaoCK{

	public List<UserInfoCK> query(String sql) {
		String[] columns = {"username","password","email"};
		return query(sql,columns);
	}

	public int insert(UserInfoCK userinfock) {
		String table = "USERINFOCK";
		int row;
		boolean flag;
		List<String> list = new ArrayList<String>();
		
		list.add(userinfock.getUsernameCK());
		list.add(userinfock.getPasswordCK());
		list.add(userinfock.getEmailCK());
		row = insert(table, list);
		
		return row;
	}

	
	
}
