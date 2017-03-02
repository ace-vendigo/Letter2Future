package com.github.vendigo.l2f.backend.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/verification")
public class VerificationController {
    @Autowired
    VerificationService verificationService;

    @RequestMapping(value = "/activate/{token}", method = RequestMethod.GET)
    public ResponseEntity<VerificationResult> activateUser(@PathVariable String token) {
        return new ResponseEntity<>(verificationService.activateUser(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/resend/{email}", method = RequestMethod.GET)
    public ResponseEntity<VerificationStatus> resendLetter(@PathVariable String email) {
        return new ResponseEntity<>(verificationService.resendLetter(email), HttpStatus.OK);
    }
}
