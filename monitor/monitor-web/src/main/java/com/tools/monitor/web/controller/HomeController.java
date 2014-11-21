package com.tools.monitor.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Rolyer Luo(rolyer.live@gmail.com) on 14-11-21 上午8:41.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final static Logger LOGGER = Logger.getLogger(HomeController.class);

    @RequestMapping("index.html")
    public void index(ModelMap out) {
        LOGGER.debug("invoked HomeController: index");
        out.put("message", "User List:");
    }
}
