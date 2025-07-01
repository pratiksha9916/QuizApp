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
//        entityManagerFactoryRef = "api1EntityManager",
//        transactionManagerRef = "api1TransactionManager"
//)
//
//public class configClass1 {
//    @Bean
//    @ConfigurationProperties(prefix = "datasource.api1")
//    public DataSource api1DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean api1EntityManager(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(api1DataSource())
//                .packages("com.example.QuizApp.configclass")
//                .persistenceUnit("api1")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager api1TransactionManager(
//            @Qualifier("api1EntityManager") EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
//
// @Bean(name = "entityManagerFactoryBuilder1")
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
//            ObjectProvider<JpaVendorAdapter> jpaVendorAdapter,
//            ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
//        return new EntityManagerFactoryBuilder(
//                jpaVendorAdapter.getIfAvailable(),
//                new HashMap<>(),
//                persistenceUnitManager.getIfAvailable()
//        );
//
//    }
//}
//
