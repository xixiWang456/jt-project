package com.jt.manage.mapper;

import java.util.List;

import com.jt.manage.pojo.User;

public interface UserMapper {

	/**
	 * 查询user表的全部信息
	 * @return
	 */
	List<User> findAll();
}
