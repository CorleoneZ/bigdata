package com.jpa.data.config;

import com.jpa.data.repo.WechatUserRepo;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EntityScan(basePackages = "com.jpa.data.entity")
@EnableJpaRepositories(
        basePackages = "com.jpa.data",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "transactionManager",
        enableDefaultTransactions=true)
public class JPAConfig {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    // JPA扩展配置
    @Resource
    private JpaProperties jpaProperties;

    // 实体管理工厂
    @Resource
    private EntityManagerFactoryBuilder factoryBuilder;

    @Resource
    private HibernateProperties hibernateProperties;

    /**
     * 配置第二个实体管理工厂的bean
     */
    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        return factoryBuilder.dataSource(dataSource)
                .properties(getVendorProperties())
                .packages("com.jpa.data.entity")
                .persistenceUnit("persistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties() {
        Map<String, String> properties = jpaProperties.getProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return hibernateProperties.determineHibernateProperties(properties, new HibernateSettings());
    }

    @Bean(name = "entityManager")
    public EntityManager entityManager() {
        return entityManagerFactoryBean().getObject().createEntityManager();
    }

    /**
     * jpa事务管理
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
}
