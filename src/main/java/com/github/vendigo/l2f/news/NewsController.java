package com.github.vendigo.l2f.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    @GetMapping("/api/news")
    public String news() {
        return "Latest news!";
    }
}
