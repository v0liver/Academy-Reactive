package it.reactive.demoTorneoSpringBatch.Configuration;

import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Profile("!test")
@EnableTransactionManagement
public class DataSourceConfig {


    @Bean(name = Costanti.dataSourceTorneo)
    @ConfigurationProperties(prefix = "spring.datasource-torneo")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("it.reactive.demoTorneoSpringBatch.model");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        return em;
//    }
//
//    @Primary
//    @Bean(name = "torneoTransactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("torneoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }

    @Bean(name = "dataSource")
    //@BatchDataSource
    @Primary
    public DataSource H2Datasource() {
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
