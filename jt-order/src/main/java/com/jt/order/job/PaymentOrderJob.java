package com.jt.order.job;

import java.util.Date;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.jt.order.mapper.OrderMapper;

public class PaymentOrderJob extends QuartzJobBean{

	//连接容器的纽带context
	/**
	 * 1.通过定时任务的上下文取得spring容器
	 * 2.通过spring容器取得bean对象
	 * 3.获取对象后执行具体的操作
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getJobDetail().getJobDataMap().get("applicationContext");
		OrderMapper orderMappepr = applicationContext.getBean(OrderMapper.class);
		//2天没有支付表示超时
		Date ageDate=new DateTime().minusDays(2).toDate();
		orderMappepr.updateStatus(ageDate);
		System.out.println("修改成功！");
	}

}
