package com.iot.integrationtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@TestPropertySource(locations = "classpath:application-test.properties")
public class H2TestConfiguration {
	
	@Value("${h2.datasource.driver}")
	private String driver;
	@Value("${h2.datasource.url}")
	private String url;
	
	@Bean
    @Profile("test")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);

        return dataSource;
    }
}
