package com.jt.manage.service;

import java.util.List;

import com.jt.manage.pojo.User;

public interface UserService {

	/**
	 * 查询user表的全部信息
	 * @return
	 */
	List<User> findAll();
	
	
}
