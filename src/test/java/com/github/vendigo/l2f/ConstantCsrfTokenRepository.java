package com.github.vendigo.l2f;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConstantCsrfTokenRepository implements CsrfTokenRepository {

    private final String tokenValue;
    private final String headerName;
    private final String parameterName;

    public ConstantCsrfTokenRepository(String tokenValue, String headerName, String parameterName) {
        this.tokenValue = tokenValue;
        this.headerName = headerName;
        this.parameterName = parameterName;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(headerName, parameterName, tokenValue);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(parameterName);
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(parameterName, token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute(parameterName);
    }
}
