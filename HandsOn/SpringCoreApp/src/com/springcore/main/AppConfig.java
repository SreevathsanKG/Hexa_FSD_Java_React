package com.springcore.main;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "com.springcore.main")  // TO scan packages
public class AppConfig {  // Central Configuration Class

	static {
		System.out.println("Successfull Config");
	}
	
	@Bean
	public DataSource getDataSourse() {
		String url = "jdbc:mysql://localhost:3306/lms_db";
		String user = "root";
		String password = "root@123";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, password);
		dataSource.setDriverClassName(driver);
		
		return dataSource;
	}
	
	@Bean
	JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}