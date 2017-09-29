package ua.nure.zhazhky.SummaryTask4.web.listener;

import org.apache.log4j.PropertyConfigurator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.WebUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("/*")
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("Context init started");

        ServletContext servletContext = servletContextEvent.getServletContext();
        initLog4j(servletContext);

        log("Context init finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Context destroy started");

        WebUtil.getService().shutdownNow();

        log("Context destroy finished");
    }

    private void initLog4j(ServletContext servletContext) {
        log("Log4j init started");

        PropertyConfigurator.configure(
                servletContext.getRealPath("WEB-INF/resources/log4j.properties"));

        log("Log4j init finished");
    }

    private void log(String message) {
        System.out.println("[ContextListener] " + message);
    }
}
