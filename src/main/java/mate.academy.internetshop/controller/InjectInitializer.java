package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import mate.academy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

@WebListener
public class InjectInitializer implements ServletContextListener {

    private static final Logger log = Logger.getLogger(InjectInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            log.info("Dependency injection started");
            Injector.injectDependency();
        } catch (IllegalAccessException exception) {
            log.error("Dependency injection failed" + exception);

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
