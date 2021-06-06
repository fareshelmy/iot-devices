package com.iot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

	@Value("${mysql.db.driver}")
	private String driver;
	@Value("${mysql.db.url}")
	private String url;
	@Value("${mysql.db.username}")
	private String username;
	@Value("${mysql.db.password}")
	private String password;
	
    @Bean
    @Profile("production")
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
    	dataSource.setDriverClassName(driver);
        return dataSource;
    }
}
