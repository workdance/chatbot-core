package com.workdance.chatbot.dal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Bean(name = "singleDataSource")
  @Primary
  public DataSource dataSource1() {
    return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driverClassName)
            .build();
  }

  @Bean
  public TransactionManager txManagerForSingle(@Qualifier(value = "singleDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
