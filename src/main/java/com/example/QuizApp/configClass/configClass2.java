//package com.example.QuizApp.configclass;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.example.QuizApp.Repository",
//        entityManagerFactoryRef = "api2EntityManager",
//        transactionManagerRef = "api2TransactionManager"
//)
//public class configClass2 {
//
//    @Bean
//    @ConfigurationProperties(prefix = "datasource.api2")
//    public DataSource api2DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean api2EntityManager(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(api2DataSource())
//                .packages("com.example.QuizApp.configclass")
//                .persistenceUnit("api2")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager api2TransactionManager(
//            @Qualifier("api2EntityManager") EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
//
//    @Bean(name = "entityManagerFactoryBuilder2")
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
//            ObjectProvider<JpaVendorAdapter> jpaVendorAdapter,
//            ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
//        return new EntityManagerFactoryBuilder(
//                jpaVendorAdapter.getIfAvailable(),
//                new HashMap<>(),
//                persistenceUnitManager.getIfAvailable()
//        );
//    }
//}