package com.github.vendigo.l2f.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestCsrfTokenRepository implements CsrfTokenRepository {
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken("X-XSRF-TOKEN", "_csrf", "test_csrf_token");
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute("XSRF-TOKEN");
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("XSRF-TOKEN", token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute("XSRF-TOKEN");
    }
}
