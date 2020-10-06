package com.samuell.rhino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RhinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhinoApplication.class, args);
		System.out.println("som debil");
	}

}
