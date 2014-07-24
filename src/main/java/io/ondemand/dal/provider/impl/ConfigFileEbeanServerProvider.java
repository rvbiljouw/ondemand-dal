package io.ondemand.dal.provider.impl;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;

import javax.inject.Provider;
import java.io.IOException;
import java.util.Properties;

/**
 * Live data source provider
 *
 * @author rvbiljouw
 */
public final class ConfigFileEbeanServerProvider implements Provider<EbeanServer> {

    @Override
    public EbeanServer get() {
        try {
            final Properties properties = new Properties();
            properties.load(ConfigFileEbeanServerProvider.class.getResourceAsStream("/database.properties"));
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setDriver(properties.getProperty("database.driver"));
            dataSourceConfig.setUsername(properties.getProperty("database.username"));
            dataSourceConfig.setPassword(properties.getProperty("database.password"));
            dataSourceConfig.setUrl(properties.getProperty("database.connection_string"));

            ServerConfig serverConfig = new ServerConfig();
            serverConfig.setDdlGenerate(properties.getProperty("database.generate_ddl", "false").equals("true"));
            serverConfig.setDdlRun(properties.getProperty("database.run_ddl", "false").equals("true"));
            serverConfig.addPackage(properties.getProperty("models.packages"));
            serverConfig.setDefaultServer(true);
            serverConfig.setRegister(false);

            return EbeanServerFactory.create(serverConfig);
        } catch (IOException e) {
            throw new IllegalStateException("Something went very, very wrong..", e);
        }
    }

}
