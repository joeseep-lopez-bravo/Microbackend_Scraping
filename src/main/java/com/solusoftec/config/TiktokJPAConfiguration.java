package com.solusoftec.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.solusoftec.repositories.tiktok", // Repositorios de tiktok
        entityManagerFactoryRef = "tiktokEntityManagerFactory",
        transactionManagerRef = "tiktokTransactionManager"
)
public class TiktokJPAConfiguration {

    @Value("${spring.datasource.tiktok.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.tiktok.username}")
    private String username;

    @Value("${spring.datasource.tiktok.password}")
    private String password;

    @Value("${spring.datasource.tiktok.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "tiktokDataSource")
    public DataSource tiktokDataSource() {
        System.out.println("JdbcUrl: " + jdbcUrl);
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Primary
    @Bean(name = "tiktokEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tiktokEntityManagerFactory(
            @Qualifier("tiktokDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.solusoftec.entities.tiktok"); // Entidades de tiktok
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        return em;
    }

    @Primary
    @Bean(name = "tiktokTransactionManager")
    public PlatformTransactionManager tiktokTransactionManager(
            @Qualifier("tiktokEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
}
