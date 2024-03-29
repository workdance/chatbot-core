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
 * 演示多数据源场景的配置方式，使用前请先取消@Configuration注解的注释
 * 使用 mybatis-generator 生成 DAO 层代码请参考文档：https://yuque.antfin.com/docs/share/fa3a42c9-d26a-42d4-a3d1-d3252db16fd8?# 《在 SOFABoot 应用中使用 MyBatis Generator》
 */
@Configuration
@MapperScan(basePackages = "com.workdance.chatbot.dal.mybatis.mapper.aichatbot", sqlSessionFactoryRef = "sqlSessionFactoryBeanForMP")
@MapperScan(basePackages = "com.workdance.chatbot.dal.mybatisplus.mapper", sqlSessionFactoryRef = "sqlSessionFactoryBeanForMP")
public class MybatisConfiguration {

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBeanForChatbot(@Qualifier(value = "singleDataSource") DataSource dataSource) throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:sqlmap/**/**.xml"));
//        return sqlSessionFactoryBean;
//    }

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
