package com.jt.order.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import com.jt.common.mapper.SysMapper;
import com.jt.order.pojo.Order;

public interface OrderMapper extends SysMapper<Order>{

	void updateStatus(@Param("agoDate") Date agoDate);

	
}
