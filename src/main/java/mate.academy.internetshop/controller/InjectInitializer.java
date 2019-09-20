package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import mate.academy.internetshop.lib.Injector;

@WebListener
public class InjectInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Dependency injection started");
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
