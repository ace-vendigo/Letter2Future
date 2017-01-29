package com.github.vendigo.l2f.verification;

import lombok.Data;

@Data
public class VerificationLetter {
    private String email;
    private String subject;
    private String username;
    private String body;
}
