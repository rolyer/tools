package com.tools.monitor.web.controller;

import com.tools.monitor.Monitor;
import com.tools.monitor.startup.Server;
import com.tools.monitor.web.dto.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rolyer Luo(rolyer.live@gmail.com) on 14-11-21 上午8:41.
 */
@Controller
@RequestMapping("/")
public class HomeController extends MonitorServerSupport {
    private final static Logger LOGGER = Logger.getLogger(HomeController.class);

    private List<Monitor> monitors;

    @RequestMapping("index.html")
    public void index(ModelMap out) {
        LOGGER.debug("invoked HomeController: index");
        out.put("message", "User List:");

        Server server = getServer();

        if (server != null) {
            monitors = new LinkedList<Monitor>(server.getMonitors().values());

            out.put("monitors", monitors);
            out.put("total", monitors.size());

            for (Monitor monitor : monitors) {
                LOGGER.info("Monitor name: " + monitor.getName());
                LOGGER.info("Monitor name: " + monitor.getFile().getPath());
            }
        }
    }

    @RequestMapping(value = "operation.html", method = RequestMethod.POST)
    public @ResponseBody Result operation(String name, String operation){
        Server server = getServer();
        Result result = new Result();
        if (server != null) {
            Monitor monitor = server.getMonitors().get(name);
            try {
                if (monitor == null) {
                    LOGGER.info("Unexist monitor '" + name + "'.");
                } else {
                    if ("stop".equals(operation)) {
                        monitor.stop();
                        result.setSuccess(true);
                    } else if ("start".equals(operation)) {
                        monitor.start();
                        result.setSuccess(true);
                    } else {

                    }
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
        }

        return  result;
    }
}
