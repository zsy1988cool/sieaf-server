package com.ylz.seaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 86189 on 2019/5/19.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/greeting")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/greeting");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }
}
