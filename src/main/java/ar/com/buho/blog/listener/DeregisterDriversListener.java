package ar.com.buho.blog.listener;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Driver;

public class DeregisterDriversListener implements ServletContextListener {

	protected static Logger logger = Logger.getLogger("interceptor");
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = (Driver) drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.debug("Deregistering jdbc driver: " + driver);
            } catch (SQLException e) {
                logger.debug("Error deregistering driver " + driver + " " + e);
            }

        }
		
	}
	
}
