/**    package com.solusoftec.config;

    import org.hibernate.jpa.HibernatePersistenceProvider;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.boot.autoconfigure.domain.EntityScan;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
    import org.springframework.orm.jpa.JpaTransactionManager;
    import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
    import org.springframework.transaction.PlatformTransactionManager;

    import jakarta.persistence.EntityManagerFactory;
    import javax.sql.DataSource;

    @Configuration
    @EnableJpaRepositories(
            basePackages =
                    "com.solusoftec.repositories",           // Paquete para repositorios de Facebook
            entityManagerFactoryRef = "twitterEntityManagerFactory", // Ref. a la configuración de Facebook
            transactionManagerRef = "twitterTransactionManager"      // Transacción de Facebook
    )

    @EntityScan(basePackages = "com.solusoftec.entities")
    public class JPAconfiguration {

        // Configuración para Facebook
        @Qualifier("facebook_db")
        @Bean(name = "facebookEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean facebookEntityManagerFactory(
                @Qualifier("facebookDataSource") DataSource facebookDataSource) {
            LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
            emfb.setDataSource(facebookDataSource);
            emfb.setPersistenceProvider(new HibernatePersistenceProvider());
            emfb.setPackagesToScan("com.solusoftec.entities");  // Paquete de entidades de Facebook
            return emfb;
        }
        @Qualifier("facebook_db")
        @Bean(name = "facebookTransactionManager")
        public PlatformTransactionManager facebookTransactionManager(
                @Qualifier("facebookEntityManagerFactory") EntityManagerFactory facebookEntityManagerFactory) {
            return new JpaTransactionManager(facebookEntityManagerFactory);
        }

        // Configuración para Twitter
        @Qualifier("xtwitter_db")
        @Bean(name = "twitterEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean twitterEntityManagerFactory(
                @Qualifier("twitterDataSource") DataSource twitterDataSource) {
            LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
            emfb.setDataSource(twitterDataSource);
            emfb.setPersistenceProvider(new HibernatePersistenceProvider());
            emfb.setPackagesToScan("com.solusoftec.entities");  // Paquete de entidades de Twitter
            return emfb;
        }
        @Qualifier("xtwitter_db")
        @Bean(name = "twitterTransactionManager")
        public PlatformTransactionManager twitterTransactionManager(
                @Qualifier("twitterEntityManagerFactory") EntityManagerFactory twitterEntityManagerFactory) {
            return new JpaTransactionManager(twitterEntityManagerFactory);
        }
    }**/
