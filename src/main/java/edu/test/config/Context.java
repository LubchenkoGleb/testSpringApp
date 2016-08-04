package edu.test.config;

/**
 * Created by gleb-pc on 8/3/16.
 */

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;



@Configuration
@EnableTransactionManagement
@ComponentScan("edu.test")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("edu.test.repository")
public class Context {

    private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_DATABASE_URL = "db.url";
    private static final String PROPERTY_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
    private static final String PROPERTY_HIBERNATE_ENABLE_LAZY = "hibernate.enable_lazy_load_no_trans";
    private static final String PROPERTY_HIBERNATE_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_HIBERNATE_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();

        properties.put(PROPERTY_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
        properties.put(PROPERTY_HIBERNATE_DEFAULT_SCHEMA, environment.getRequiredProperty(PROPERTY_HIBERNATE_DEFAULT_SCHEMA));
        properties.put(PROPERTY_HIBERNATE_ENABLE_LAZY, environment.getRequiredProperty(PROPERTY_HIBERNATE_ENABLE_LAZY));
        properties.put(PROPERTY_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        properties.put(PROPERTY_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}