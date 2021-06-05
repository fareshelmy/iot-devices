package com.iot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

	@Value("${mysql_db_driver}")
	private String driver;
	@Value("${mysql_db_url}")
	private String url;
	@Value("${mysql_db_username}")
	private String username;
	@Value("${mysql_db_password}")
	private String password;
	
    @Bean
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
    	dataSource.setDriverClassName(driver);
        return dataSource;
    }
}
