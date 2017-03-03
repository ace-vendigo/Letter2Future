package com.github.vendigo.l2f.backend.home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/news")
    public ResponseEntity<String> news() {
        return new ResponseEntity<>("Hot news!", HttpStatus.OK);
    }
}
