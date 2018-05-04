package com.appointment.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RichgoMain {
	public static void main(String[] args){
//		com.alibaba.dubbo.container.Main.main(args);
		//http://www.yiibai.com/javalang/runtime_addshutdownhook.html
		//java.lang.Runtime.addShutdownHook(Thread hook) 方法注册一个新的虚拟机关闭挂钩。 Java虚拟机的关机响应于两种类型的事件
		
//		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				 "classpath:spring/applicationContext.xml");

	        context.start();

	        synchronized (RichgoMain.class) {
	            while (true) {
	                try {
	                	RichgoMain.class.wait();
	                } catch (Throwable e) {
	                }
	            }
	        }
	}
}
