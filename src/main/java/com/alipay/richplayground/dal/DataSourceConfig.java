package com.alipay.richplayground.dal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
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

  @Bean(name = "singleDataSource")
  @Primary
  public DataSource dataSource1() {
    return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/richplayground")
            .username("root")
            .password("U5i3C572")
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();
  }

  @Bean
  public TransactionManager txManagerForSingle(@Qualifier(value = "singleDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
