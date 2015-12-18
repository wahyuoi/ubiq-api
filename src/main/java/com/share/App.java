package com.share;

import com.share.core.Uploaded;
import com.share.core.User;
import com.share.resources.*;
import io.dropwizard.Application;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App extends Application<AppConfiguration>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }

    private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(
            User.class, Uploaded.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration appConfiguration) {
            return appConfiguration.getDataSourceFactory();
        }
    };

    private final MigrationsBundle<AppConfiguration> migration = new MigrationsBundle<AppConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration appConfiguration) {
            return appConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(migration);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        LOGGER.info("Method App#run() called, starting application");

        SessionFactory hibernateSessionFactory = hibernate.getSessionFactory();

        // add resource to env
        environment.jersey().register(new UploadResource(hibernateSessionFactory, appConfiguration.getImagePath()));
        environment.jersey().register(new UserResource(hibernateSessionFactory));
    }
}
