package com.samuell.rhino;


import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RhinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhinoApplication.class, args);

//		ApplicationContext kontext = SpringApplication.run(RhinoApplication.class, args);
////		a)
//		LogServiceImpl logService = kontext.getBean(LogServiceImpl.class);
//		System.out.println(logService.getAllLogs());
//
////		b)
//		UserServiceImpl  userService = kontext.getBean(UserServiceImpl .class);
//		System.out.println(userService.getAllUsers());
	}

}
