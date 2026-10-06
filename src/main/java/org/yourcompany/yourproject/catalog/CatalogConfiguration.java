package org.yourcompany.yourproject.catalog;

import java.util.LinkedHashMap;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
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

@Configuration (proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = "org.yourcompany.yourproject.catalog",
            entityManagerFactoryRef = "catalogEntityManagerFactory",
            transactionManagerRef = "catalogTransactionManager"
)
public class CatalogConfiguration {
    @Qualifier("catalog")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("catalog.datasource")
    public DataSourceProperties catalogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("catalog")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("catalog.datasource.configuration")
    public HikariDataSource catalogDataSource(@Qualifier("catalog") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Qualifier("catalog")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("catalog.jpa")
    public JpaProperties catalogJpaProperties() {
        return new JpaProperties();
    }

    @Qualifier("catalog")
    @Bean(defaultCandidate = false)
    public LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory(@Qualifier("catalog") DataSource dataSource, @Qualifier("catalog") JpaProperties jpaProperties) {
        var builder = new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), x -> new LinkedHashMap<>(jpaProperties.getProperties()), null);
        return builder
            .dataSource(dataSource)
            .packages("org.yourcompany.yourproject.catalog")
            .persistenceUnit("catalog")
            .build();
    }

    @Qualifier("catalog")
    @Bean
    public PlatformTransactionManager catalogTransactionManager(@Qualifier("catalog") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
}
