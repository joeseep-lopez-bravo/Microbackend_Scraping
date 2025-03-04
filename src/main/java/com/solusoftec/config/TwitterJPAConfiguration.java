package com.solusoftec.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.solusoftec.repositories.twitter", // Repositorios de twitter
        entityManagerFactoryRef = "twitterEntityManagerFactory",
        transactionManagerRef = "twitterTransactionManager"
)
public class TwitterJPAConfiguration {

    @Value("${spring.datasource.twitter.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.twitter.username}")
    private String username;

    @Value("${spring.datasource.twitter.password}")
    private String password;

    @Value("${spring.datasource.twitter.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "twitterDataSource")
    public DataSource twitterDataSource() {
        System.out.println("JdbcUrl: " + jdbcUrl);
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Primary
    @Bean(name = "twitterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean twitterEntityManagerFactory(
            @Qualifier("twitterDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.solusoftec.entities.twitter"); // Entidades de twitter
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        return em;
    }

    @Primary
    @Bean(name = "twitterTransactionManager")
    public PlatformTransactionManager twitterTransactionManager(
            @Qualifier("twitterEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
