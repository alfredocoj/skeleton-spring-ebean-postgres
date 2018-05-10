package br.uema.application.config;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.spring.txn.SpringJdbcTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ebeanConfig")
    public ServerConfig ebeanServerConfig(@Qualifier("dataSource") DataSource dataSource) {
        ServerConfig config = new ServerConfig();

        config.setName("skeleton");
        config.setDefaultServer(false);
        config.setDataSource(dataSource);
        config.addPackage("br.uema.application.entities");

        config.setExternalTransactionManager(new SpringJdbcTransactionManager());
        config.setAutoCommitMode(false);
        config.setExpressionNativeIlike(true);
        config.setRegister(true);

        return config;

    }

    @Primary
    @Bean(name = "ebeanServer")
    public EbeanServer ebeanServer(@Qualifier("ebeanConfig") ServerConfig serverConfig) {
        return EbeanServerFactory.create(serverConfig);
    }
}
