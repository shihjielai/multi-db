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
        entityManagerFactoryRef = "mallEntityManagerFactory",
        transactionManagerRef = "mallTransactionManager",
        basePackages = {"com.example.multidb.mall.repository"}
)
public class MallDbConfiguration {

    @Bean(name = "mallDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mall")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mallEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mallEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mallDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.multidb.mall.entity")
                .persistenceUnit("mall")
                .build();
    }

    @Bean(name = "mallTransactionManager")
    public PlatformTransactionManager mallTransactionManager(@Qualifier("mallEntityManagerFactory") EntityManagerFactory mallEntityManagerFactory) {
        return new JpaTransactionManager(mallEntityManagerFactory);
    }
}
