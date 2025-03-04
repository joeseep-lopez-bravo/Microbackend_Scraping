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
        basePackages = "com.solusoftec.repositories.instagram", // Repositorios de instagram
        entityManagerFactoryRef = "instagramEntityManagerFactory",
        transactionManagerRef = "instagramTransactionManager"
)
public class InstagramJPAConfiguration {
    @Value("${spring.datasource.instagram.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.instagram.username}")
    private String username;

    @Value("${spring.datasource.instagram.password}")
    private String password;

    @Value("${spring.datasource.instagram.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "instagramDataSource")
    public DataSource instagramDataSource() {
        System.out.println("JdbcUrl: " + jdbcUrl);
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Primary
    @Bean(name = "instagramEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean instagramEntityManagerFactory(
            @Qualifier("instagramDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.solusoftec.entities.instagram"); // Entidades de instagram
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        return em;
    }

    @Primary
    @Bean(name = "instagramTransactionManager")
    public PlatformTransactionManager instagramTransactionManager(
            @Qualifier("instagramEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
    
}
