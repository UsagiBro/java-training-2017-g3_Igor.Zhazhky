package ua.nure.zhazhky.SummaryTask4.web.webUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class WebUtil {

    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

    public static ScheduledExecutorService getService() {
        return service;
    }
}
