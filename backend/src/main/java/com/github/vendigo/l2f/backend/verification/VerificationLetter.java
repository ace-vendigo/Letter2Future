package com.github.vendigo.l2f.backend.verification;

import lombok.Data;

@Data
public class VerificationLetter {
    private String email;
    private String subject;
    private String username;
    private String body;
}
