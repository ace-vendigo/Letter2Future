package com.github.vendigo.l2f.home;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public Map<String, String> news() {
        Map<String, String> response = new HashMap<>();
        response.put("news", "Latest news!");
        return response;
    }
}
