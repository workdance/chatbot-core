package com.workdance.chatbot.dal;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.workdance.chatbot.dal.mybatisplus.handler.MyMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 使用前请先取消@Configuration注解的注释
 */
@Configuration
//@MapperScan(basePackages = "com.workdance.chatbot.dal.mybatis.mapper.aichatbot", sqlSessionFactoryRef = "sqlSessionFactoryBeanForMP")
@MapperScan(basePackages = "com.workdance.chatbot.dal.mybatisplus.mapper", sqlSessionFactoryRef = "sqlSessionFactoryBeanForMP")
public class MybatisConfiguration {

  @Bean
  public MybatisSqlSessionFactoryBean sqlSessionFactoryBeanForMP(@Qualifier(value = "singleDataSource") DataSource dataSource) throws IOException {
    MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources("classpath:sqlmap/**/**.xml"));
    sqlSessionFactoryBean.setGlobalConfig(new GlobalConfig().setMetaObjectHandler(new MyMetaObjectHandler()));
    return sqlSessionFactoryBean;
  }

}
