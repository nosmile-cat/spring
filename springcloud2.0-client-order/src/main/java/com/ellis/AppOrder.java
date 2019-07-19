package com.ellis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages="com.ellis.order.dao")
public class AppOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AppOrder.class, args);
	}

}
