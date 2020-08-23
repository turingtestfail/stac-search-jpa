package com.jm.stacsearchjpa.util;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.postgis.PostGISDialect;
import org.geotools.data.postgis.PostgisFilterToSQL;
import org.geotools.data.postgis.PostgisNGDataStoreFactory;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.JDBCDataStoreFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PostgisFactory {
    private final Environment env;
    public PostgisFactory(Environment env){
        this.env = env;
    }
    @Bean
    public PostgisFilterToSQL postgisFilterToSQL() throws Exception {
        //https://stackoverflow.com/a/9287197
        String url = env.getProperty("spring.datasource.url");
        String cleanURI = url.substring(5);
        URI uri = URI.create(cleanURI);

        Map<String, Serializable> params = new HashMap<>();
        params.put(PostgisNGDataStoreFactory.DBTYPE.key, "postgis");
        params.put(PostgisNGDataStoreFactory.HOST.key, uri.getHost());
        params.put(PostgisNGDataStoreFactory.PORT.key, uri.getPort());
        params.put(PostgisNGDataStoreFactory.DATABASE.key, uri.getPath().substring(1));
        params.put(PostgisNGDataStoreFactory.USER.key, env.getProperty("spring.datasource.username"));
        params.put(PostgisNGDataStoreFactory.PASSWD.key, env.getProperty("spring.datasource.password"));
        params.put(PostgisNGDataStoreFactory.FETCHSIZE.key, 1000);
        params.put(PostgisNGDataStoreFactory.EXPOSE_PK.key, true);
        DataStore dataStore;
        try {
            dataStore = DataStoreFinder.getDataStore(params);
        } catch (IOException e) {
            throw new Exception("Unable to connect using the specified database parameters.", e);
        }
        if (dataStore == null) {
            throw new Exception("Unable to connect using the specified database parameters.");
        }
        if (dataStore instanceof JDBCDataStore) {
            Connection con = null;
            try {
                con = ((JDBCDataStore) dataStore).getDataSource().getConnection();
            } catch (SQLException e) {
                throw new Exception(e.getMessage(), e);
            }
            ((JDBCDataStore) dataStore).closeSafe(con);
        }
        return new PostgisFilterToSQL(new PostGISDialect((JDBCDataStore)dataStore));
    }
}
