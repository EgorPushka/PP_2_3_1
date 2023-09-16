package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class HibernateCfg {

    private final Environment env;

    @Autowired
    public HibernateCfg(Environment env) {
        this.env = env;
    }

    private Properties hiberProperties() {
        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getProperty("dialect"));
        properties.put("hibernate.show_sql", env.getProperty("show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hbm2ddl.auto"));
//        properties.put("hibernate.hbm2ddl.import_files", env.getProperty("hbm2ddl.import_files"));
//        properties.put("hibernate.hbm2ddl.delimiter", env.getProperty("hbm2ddl.delimiter"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));

        ds.setInitialSize(Integer.parseInt(env.getRequiredProperty("initSize")));
        ds.setMinIdle(Integer.parseInt(env.getRequiredProperty("minIdle")));
        ds.setMaxIdle(Integer.parseInt(env.getRequiredProperty("maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getRequiredProperty("timeBtwEvictRunsMills")));
        ds.setMinEvictableIdleTimeMillis(Long.parseLong(env.getRequiredProperty("minEvicTimeIdleMills")));
        ds.setTestOnBorrow(Boolean.parseBoolean(env.getRequiredProperty("testOnBorrow")));
        ds.setValidationQuery(env.getRequiredProperty("validationQuery"));
        return ds;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
//        sf.setDataSource(dataSource());
//        sf.setPackagesToScan("web.models");
//        sf.setHibernateProperties(hiberProperties());
//        return sf;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }

    @Bean //for EntityManager
    public LocalContainerEntityManagerFactoryBean sessionFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("web");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hiberProperties());
        return em;
    }

    @Bean //for EntityManager
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(sessionFactory().getObject());
        return manager;
    }

}
