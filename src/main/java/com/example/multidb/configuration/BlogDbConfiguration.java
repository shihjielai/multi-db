package com.example.multidb.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "blogEntityManagerFactory",
        transactionManagerRef = "blogTransactionManager",
        basePackages = {"com.example.multidb.blog.repository"}
)
public class BlogDbConfiguration {

    @Bean(name = "blogDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.blog")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "blogEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean blogEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("blogDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.multidb.blog.entity")
                .persistenceUnit("blog")
                .build();
    }

    @Bean(name = "blogTransactionManager")
    public PlatformTransactionManager blogTransactionManager(@Qualifier("blogEntityManagerFactory") EntityManagerFactory blogEntityManagerFactory) {
        return new JpaTransactionManager(blogEntityManagerFactory);
    }

}
