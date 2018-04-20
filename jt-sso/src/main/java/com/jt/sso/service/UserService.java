package com.jt.sso.service;

import com.jt.sso.pojo.User;

public interface UserService {

	Boolean findCheckedUser(String column, String param);

	int saveRegisterUser(User user);

	String findUserByUP(String username, String password);

	String findUserInfo(String ticket);


}
