package org.slam.publicshare.config.security;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.config.security.filter.RestAuthenticationFilter;
import org.slam.publicshare.config.security.handler.AuthDeniedHandler;
import org.slam.publicshare.config.security.handler.AuthFailureHandler;
import org.slam.publicshare.config.security.handler.AuthSuccessHandler;
import org.slam.publicshare.config.security.handler.UnauthorizedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountFindService accountFindService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedHandler() {
        return new UnauthorizedHandler();
    }

    @Bean
    public AccessDeniedHandler authDeniedHandler() {
        return new AuthDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authSuccessHandler() {
        return new AuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authFailureHandler() {
        return new AuthFailureHandler();
    }

    @Bean
    public RestAuthenticationFilter authenticationFilter() throws Exception {
        var filter = new RestAuthenticationFilter(authenticationManager());

        filter.setFilterProcessesUrl("/auth/sign-in");

        filter.setAuthenticationSuccessHandler(authSuccessHandler());
        filter.setAuthenticationFailureHandler(authFailureHandler());

        // check authenticationManager
        filter.afterPropertiesSet();

        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountFindService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/error") // spring boot default error handler
                .antMatchers("/css/**", "/js/**", "/img/**")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/account").permitAll()
                    .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler())
                    .accessDeniedHandler(authDeniedHandler())
            .and()
                .addFilter(authenticationFilter())
                .formLogin()
                    .loginProcessingUrl("/auth/sign-in")
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/sign-out")
                    .deleteCookies("JSESSIONID", "SPRING_SECURITY_REMEMBER_ME_COOKIE")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
            .and()
                .rememberMe()
                    .key("SECRET_KEY")
                    .authenticationSuccessHandler(authSuccessHandler())
                    .tokenValiditySeconds(7 * 24 * 60 * 60); // 1 week
    }

}