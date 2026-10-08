package org.yourcompany.yourproject.ticketing.infrastructure.event;

import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = "org.yourcompany.yourproject.ticketing", entityManagerFactoryRef = "ticketingEntityManagerFactory", transactionManagerRef = "ticketingTransactionManager")
public class TicketingConfiguration {
    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("ticketing.datasource")
    public DataSourceProperties ticketingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("ticketing.datasource.configuration")
    public HikariDataSource ticketingDataSource (@Qualifier("ticketing") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("ticketing.jpa")
    public JpaProperties ticketingJpaProperties() {
        return new JpaProperties();
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    public LocalContainerEntityManagerFactoryBean ticketingEntityManagerFactory(@Qualifier("ticketing") DataSource dataSource,
                                                                                @Qualifier("ticketing") JpaProperties jpaProperties) {
        var builder = new EntityManagerFactoryBuilder(
            new HibernateJpaVendorAdapter(),
            x -> new LinkedHashMap<>(jpaProperties.getProperties()),
            null);

        return builder
                .dataSource(dataSource)
                .packages("org.yourcompany.yourproject.ticketing")
                .persistenceUnit("ticketing")
                .build();
    }

    @Qualifier("ticketing")
    @Bean
    public PlatformTransactionManager ticketingTransactionManager(
            @Qualifier("ticketing") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
}