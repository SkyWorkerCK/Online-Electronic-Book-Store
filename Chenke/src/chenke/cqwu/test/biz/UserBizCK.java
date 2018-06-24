package chenke.cqwu.test.biz;

import chenke.cqwu.test.dao.UserDaoCK;
import chenke.cqwu.test.entity.UserInfoCK;



public interface UserBizCK {
	public boolean checkLoginCK(String username, String password);
	public boolean userExistsCK(String username);
	public boolean addUserCK(UserInfoCK userinfock);
	public void setUserdaock(UserDaoCK userdaock);
	public UserDaoCK getUserdaock();
}
