package com.jt.web.service;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

public interface UserService {

	String saveRegisterUser(User user);

	String findUserByUp(User user);

}
