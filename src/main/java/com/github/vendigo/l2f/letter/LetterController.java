package com.github.vendigo.l2f.letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/letter")
public class LetterController {
    @Autowired
    LetterService letterService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> createLetter(@RequestBody Letter letter) {
        letterService.createLetter(letter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
