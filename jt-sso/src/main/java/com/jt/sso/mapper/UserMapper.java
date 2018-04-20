package com.jt.sso.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;

public interface UserMapper extends SysMapper<User>{

	int findCheckedUser(@Param("column") String column, @Param("param") String param);

	int  saveUser(@Param("user") User user);

	User findUserByUP(@Param("username") String username, @Param("password") String password); 
	
}
