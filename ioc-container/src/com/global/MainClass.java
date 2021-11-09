package com.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainClass {
	
	
	public static void main(String[] args) {
		
//		Employee emp = new Employee();
		
//		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
		
//		ApplicationContext context =
//		       new FileSystemXmlApplicationContext("C:\\Users\\DELL\\Documents\\global-workspace\\ioc-container\\src\\beans.xml");
		
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml"); 
				 
		AnnotationConfigApplicationContext ctx = 
		         new AnnotationConfigApplicationContext(BeanConfig.class);	
		
//		ctx.register(context);
		
		Employee emp = (Employee) context.getBean("emp");
		
		emp.setName("Ali");
		emp.setSalary(5222.0);
		
		emp.printName();
		
//		context.registerShutdownHook();	
		context.close();
		
	}

}
