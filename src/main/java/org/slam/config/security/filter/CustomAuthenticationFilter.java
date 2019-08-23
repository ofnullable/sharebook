package org.slam.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slam.config.security.userdetails.SignInRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String username;
    private String password;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        readBody(req);

        if (StringUtils.isEmpty(username) || password == null) {
            return null;
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(req, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void readBody(HttpServletRequest req) {
        try {
            var stringified = IOUtils.toString(req.getReader());
            var body = mapper.readValue(stringified, SignInRequest.class);
            setFields(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFields(SignInRequest body) {
        this.username = body.getUsername() == null ? "" : body.getUsername().trim();
        this.password = body.getPassword();
    }

}
