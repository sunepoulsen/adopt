package dk.sunepoulsen.adopt.persistence.liquibase;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.javafx.application.api.AdoptJavaFXApplicationModule;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MigrateLiquebaseDatabase extends AdoptJavaFXApplicationModule {
    private static Logger log = LoggerFactory.getLogger( MigrateLiquebaseDatabase.class );
    private Environment env;

    public MigrateLiquebaseDatabase() {
        this.env = new Environment();
    }

    @Override
    public void applicationStartup() {
        try {
            if( "false".equalsIgnoreCase( env.getString( "liquibase.migrate", "true" ) ) ) {
                return;
            }

            logDatabaseDrivers();
            String url = env.getString( "datasource.url" );
            String username = env.getString( "datasource.user" );
            String password = env.getString( "datasource.password" );

            log.info( "Start migration of {}", url );
            try( Connection connection = DriverManager.getConnection( url, username, password ) ) {
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation( new JdbcConnection( connection ) );
                Liquibase liquibase = new Liquibase( "db/changelog/changelog.xml", new ClassLoaderResourceAccessor( ClassLoader.getSystemClassLoader() ), database );

                liquibase.update( new Contexts(), new LabelExpression() );
            }
            catch( LiquibaseException | SQLException ex ) {
                log.error( "Unable to migrate {}: {}", url, ex.getMessage(), ex );
            }
        }
        catch( EnvironmentException ex ) {
            log.warn( "Unable to migrate liquibase database: {}", ex.getMessage() );
        }
    }

    @Override
    public void applicationShutdown() {
    }

    private void logDatabaseDrivers() {
        log.debug( "Supported JDBC drivers:" );
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while( drivers.hasMoreElements() ) {
            Driver driver = drivers.nextElement();
            log.debug( driver.getClass().getName() );
        }
    }
}
