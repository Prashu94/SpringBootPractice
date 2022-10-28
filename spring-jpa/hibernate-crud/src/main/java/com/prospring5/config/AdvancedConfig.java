package com.prospring5.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.prospring5")
@EnableTransactionManagement
@PropertySource("classpath:db/jdbc2.properties")
public class AdvancedConfig {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedConfig.class);
    
    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${user_name}")
    private String username;
    @Value("${password}")
    private String password;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    // Define the bean method which will be invoked by spring boot while reading the config
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        }catch(Exception e) {
            logger.error("DBCP Data Source bean is not created", e);
            return null;
        }
    }
    
    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.prospring5.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }
    
    @Bean 
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }
    
    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.format_sql", true);
        hibernateProperties.put("hibernate.use_sql_comments", true);
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.max_fetch_depth", 3);
        hibernateProperties.put("hibernate.jdbc.batch_size", 10);
        hibernateProperties.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProperties;
    }
    
    @Bean(destroyMethod="destroy")
    public CleanUp cleanup() {
        return new CleanUp(new JdbcTemplate(dataSource()));
    }
}
