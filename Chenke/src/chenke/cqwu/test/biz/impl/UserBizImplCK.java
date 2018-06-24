package chenke.cqwu.test.biz.impl;

import java.util.List;

import chenke.cqwu.test.biz.UserBizCK;
import chenke.cqwu.test.dao.UserDaoCK;
import chenke.cqwu.test.entity.UserInfoCK;

public class UserBizImplCK implements UserBizCK{

	private UserDaoCK userdaock = null;
	
	public UserDaoCK getUserdaock() {
		return userdaock;
	}
	public void setUserdaock(UserDaoCK userdaock) {
		this.userdaock = userdaock;
	}
	/*
	 * Ìí¼ÓÓÃ»§
	 * */
	public boolean addUserCK(UserInfoCK userinfock) {
		int row = userdaock.insert(userinfock);
		return row>0?true:false;
	}
	public boolean checkLoginCK(String username, String password) {
		String sql = "select * from userinfock " + "where username = '" 
				+ username + "' and password = '" + password + "'";
		List<UserInfoCK> list = userdaock.query(sql);
		return list.size()>0?true:false;
	}
	public boolean userExistsCK(String username) {
		return false;
	}
	public void setUserDaoCK(UserDaoCK userdaock) {
		
	}
	public UserDaoCK getUserDaoCK() {
		return null;
	}
}
