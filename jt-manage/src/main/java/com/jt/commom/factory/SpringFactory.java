package com.jt.commom.factory;

import org.springframework.beans.factory.FactoryBean;

import com.jt.manage.pojo.User;

public class SpringFactory implements FactoryBean<User>{

	@Override
	public User getObject() throws Exception {
		User user=new User();
		user.setAge(66);
		user.setName("liming");
		user.setSex("nu");
		user.setId(88);
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


}
