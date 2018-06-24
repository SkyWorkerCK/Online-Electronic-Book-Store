package chenke.cqwu.test.dao;

import java.util.List;

import chenke.cqwu.test.entity.UserInfoCK;

public interface UserDaoCK {
	public List<UserInfoCK> query(String sql);
	public int insert(UserInfoCK userinfock);
}
