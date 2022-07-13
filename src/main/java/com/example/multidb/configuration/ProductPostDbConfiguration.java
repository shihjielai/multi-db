package com.example.multidb.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "productPostEntityManagerFactory",
        transactionManagerRef = "productPostTransactionManager",
        basePackages = {"com.example.multidb.productPost.repository"}
)
public class ProductPostDbConfiguration {

    @Bean(name = "productPostDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.productpost")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productPostEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productPostEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productPostDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.multidb.productPost.entity")
                .persistenceUnit("productPost")
                .build();
    }

    @Bean(name = "productPostTransactionManager")
    public PlatformTransactionManager productPostTransactionManager(@Qualifier("productPostEntityManagerFactory") EntityManagerFactory productPostEntityManagerFactory) {
        return new JpaTransactionManager(productPostEntityManagerFactory);
    }
}
