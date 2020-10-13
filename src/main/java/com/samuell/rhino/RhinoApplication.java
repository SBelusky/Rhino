package com.samuell.rhino;
import com.samuell.rhino.model.status_enum.LogStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;


@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RhinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhinoApplication.class, args);
	}

}
