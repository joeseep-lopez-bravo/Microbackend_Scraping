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
        basePackages = "com.solusoftec.repositories.facebook", // Repositorios de Facebook
        entityManagerFactoryRef = "facebookEntityManagerFactory",
        transactionManagerRef = "facebookTransactionManager"
)
public class FacebookJPAConfiguration {

    @Value("${spring.datasource.facebook.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.facebook.username}")
    private String username;

    @Value("${spring.datasource.facebook.password}")
    private String password;

    @Value("${spring.datasource.facebook.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "facebookDataSource")
    public DataSource facebookDataSource() {
        System.out.println("JdbcUrl: " + jdbcUrl);
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Primary
    @Bean(name = "facebookEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean facebookEntityManagerFactory(
            @Qualifier("facebookDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.solusoftec.entities.facebook"); // Entidades de Facebook
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        return em;
    }

    @Primary
    @Bean(name = "facebookTransactionManager")
    public PlatformTransactionManager facebookTransactionManager(
            @Qualifier("facebookEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
