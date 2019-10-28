package me.ofnullable.sharebook.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.config.security.userdetails.SignInRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String username;
    private String password;
    private ObjectMapper mapper = new ObjectMapper();

    public RestAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        var authToken = readAndMakeToken(req);

        // Allow subclasses to set the "details" property
        setDetails(req, authToken);

        return this.getAuthenticationManager().authenticate(authToken);
    }

    private UsernamePasswordAuthenticationToken readAndMakeToken(HttpServletRequest req) {
        readBody(req);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    private void readBody(HttpServletRequest req) {
        try (BufferedReader reader = req.getReader()) {
            var stringified = IOUtils.toString(reader);
            var body = mapper.readValue(stringified, SignInRequest.class);
            setFields(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFields(SignInRequest body) {
        this.username = body.getUsername() == null ? "" : body.getUsername();
        this.password = body.getPassword() == null ? "" : body.getPassword();
    }

}
