package com.tools.monitor.web.controller;

import com.tools.monitor.startup.Server;
import com.tools.monitor.web.Constants;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by Rolyer Luo(rolyer.live@gmail.com) on 14-11-21 下午1:27.
 */
public class MonitorServerSupport implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public Server getServer() {
        Server server = (Server) servletContext.getAttribute(Constants.APP_SERVER);
        return server;
    }


}
