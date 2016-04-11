package com.hsjc.ssoCenter.app.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/2/17.
 */
@Controller
@RequestMapping("/restful/")
public class restController {

    @RequestMapping("skip")
    public ModelAndView restful(){
        return new ModelAndView("restful");
    }
}
