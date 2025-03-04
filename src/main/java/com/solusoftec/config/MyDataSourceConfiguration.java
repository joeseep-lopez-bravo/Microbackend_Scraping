/**package com.solusoftec.config;

    import org.springframework.boot.jdbc.DataSourceBuilder;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    import javax.sql.DataSource;

    import org.springframework.beans.factory.annotation.Value;

    @Configuration(proxyBeanMethods = false)
    public class MyDataSourceConfiguration {

        @Value("${spring.datasource.facebook.url}")
        private String jdbcUrl;

        @Value("${spring.datasource.facebook.username}")
        private String username;

        @Value("${spring.datasource.facebook.password}")
        private String password;

        @Value("${spring.datasource.facebook.driver-class-name}")
        private String driverClassName;

        @Bean
        public DataSource facebookDataSource() {
            System.out.println("JdbcUrl: " + jdbcUrl);
            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .driverClassName(driverClassName)
                    .build();
        }


        // twitter DataSource
        @Value("${spring.datasource.twitter.url}")
        private String twitterJdbcUrl;

        @Value("${spring.datasource.twitter.username}")
        private String twitterUsername;

        @Value("${spring.datasource.twitter.password}")
        private String twitterPassword;

        @Value("${spring.datasource.twitter.driver-class-name}")
        private String twitterDriverClassName;

        @Bean
        public DataSource twitterDataSource() {
            System.out.println("twitter JdbcUrl: " + twitterJdbcUrl);
            return DataSourceBuilder.create()
                    .url(twitterJdbcUrl)
                    .username(twitterUsername)
                    .password(twitterPassword)
                    .driverClassName(twitterDriverClassName)
                    .build();
        }

    }**/
