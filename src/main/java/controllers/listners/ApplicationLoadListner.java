package controllers.listners;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Created by innopolis on 24.02.17.
 */
public class ApplicationLoadListner implements ServletContextListener {
   private static final Logger logger = Logger.getLogger(ApplicationLoadListner.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.trace("gcv");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("dfg");

    }
}
