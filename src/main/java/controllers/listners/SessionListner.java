package controllers.listners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * Created by innopolis on 24.02.17.
 */
public class SessionListner implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(SessionListner.class);
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.trace("asf");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.trace("adgr");

    }
}
