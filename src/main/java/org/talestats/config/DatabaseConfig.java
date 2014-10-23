package org.talestats.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER   = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL      = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    
    private static final String PROPERTY_NAME_c3P0_MAX_SIZE = "hibernate.c3p0.max_size";
    private static final String PROPERTY_NAME_c3P0_MIN_SIZE = "hibernate.c3p0.min_size";
    private static final String PROPERTY_NAME_c3P0_TIMEOUT = "hibernate.c3p0.timeout";
    private static final String PROPERTY_NAME_c3P0_MAX_STATEMENTS = "hibernate.c3p0.max_statements";
    private static final String PROPERTY_NAME_c3P0_IDLE_TEST_PERIOD = "hibernate.c3p0.idle_test_period";
    private static final String PROPERTY_NAME_c3P0_ACQUIRE_INCREMENT = "hibernate.c3p0.acquire_increment";
    
	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		
		return dataSource;
	}
	
	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_c3P0_MAX_SIZE, env.getRequiredProperty(PROPERTY_NAME_c3P0_MAX_SIZE));
		properties.put(PROPERTY_NAME_c3P0_MIN_SIZE, env.getRequiredProperty(PROPERTY_NAME_c3P0_MIN_SIZE));
		properties.put(PROPERTY_NAME_c3P0_TIMEOUT, env.getRequiredProperty(PROPERTY_NAME_c3P0_TIMEOUT));
		properties.put(PROPERTY_NAME_c3P0_MAX_STATEMENTS, env.getRequiredProperty(PROPERTY_NAME_c3P0_MAX_STATEMENTS));
		properties.put(PROPERTY_NAME_c3P0_IDLE_TEST_PERIOD, env.getRequiredProperty(PROPERTY_NAME_c3P0_IDLE_TEST_PERIOD));
		properties.put(PROPERTY_NAME_c3P0_ACQUIRE_INCREMENT, env.getRequiredProperty(PROPERTY_NAME_c3P0_ACQUIRE_INCREMENT));
		return properties;	
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}
}