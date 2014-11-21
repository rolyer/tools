package com.tools.monitor.web.servlet;

import com.tools.monitor.config.Config;
import com.tools.monitor.config.Configurator;
import com.tools.monitor.startup.Server;
import com.tools.monitor.web.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Rolyer Luo(rolyer.live@gmail.com) on 14-11-21 上午9:11.
 */
public class StartupServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StartupServlet.class);
    private static final long serialVersionUID = -7980035112500865787L;
    private Server server = null;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        String path = servletConfig.getServletContext().getRealPath(
                Constants.CONFIG_PATH);

        Config config = null;
        try {
            config = Configurator.getConfig(path);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            config = new Config();
            LOGGER.info("Startup with empty configuration");

            //TODO: Load last process file
        }

        server = new Server(config);
        try {
            server.init();
            server.start();
        } catch (Exception e) {
            LOGGER.error("Failed startup");
            e.printStackTrace();
            throw new ServletException(e);
        }

        servletConfig.getServletContext().setAttribute(Constants.APP_SERVER,
                server);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (server != null) {
            server.destroy();
        }
    }
}
