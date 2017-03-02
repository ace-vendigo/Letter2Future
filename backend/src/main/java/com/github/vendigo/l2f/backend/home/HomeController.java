package com.github.vendigo.l2f.backend.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value = "/letters", method = RequestMethod.GET)
    public String letters() {
        return "redirect:index.html";
    }
}
