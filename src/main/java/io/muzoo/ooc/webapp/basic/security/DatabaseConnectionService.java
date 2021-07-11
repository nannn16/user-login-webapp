package io.muzoo.ooc.webapp.basic.security;

import com.zaxxer.hikari.HikariDataSource;
import io.muzoo.ooc.webapp.basic.config.ConfigProperties;
import io.muzoo.ooc.webapp.basic.config.ConfigurationLoader;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionService {

    private final HikariDataSource ds;

    public DatabaseConnectionService() {
        this.ds = new HikariDataSource();
        this.ds.setMaximumPoolSize(20);
        ConfigProperties configProperties = ConfigurationLoader.load();
        if (configProperties == null) {
            throw new RuntimeException("Unable to read config.properties.");
        }
        this.ds.setDriverClassName(configProperties.getDatabaseDriverClassName());
        this.ds.setJdbcUrl(configProperties.getDatabaseConnectionUrl());
        this.ds.addDataSourceProperty("user", configProperties.getDatabaseUsername());
        this.ds.addDataSourceProperty("password", configProperties.getDatabasePassword());
        this.ds.setAutoCommit(false);
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
}
