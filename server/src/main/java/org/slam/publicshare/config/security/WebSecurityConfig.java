package org.slam.publicshare.config.security;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.config.security.filter.RestAuthenticationFilter;
import org.slam.publicshare.config.security.handler.RestAccessDeniedHandler;
import org.slam.publicshare.config.security.handler.RestAuthFailureHandler;
import org.slam.publicshare.config.security.handler.RestAuthSuccessHandler;
import org.slam.publicshare.config.security.handler.RestUnauthorizedHandler;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

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
        return new RestUnauthorizedHandler();
    }

    @Bean
    public AccessDeniedHandler authDeniedHandler() {
        return new RestAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authSuccessHandler() {
        return new RestAuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authFailureHandler() {
        return new RestAuthFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }

    @Bean
    public RestAuthenticationFilter authenticationFilter() throws Exception {
        var filter = new RestAuthenticationFilter(authenticationManager());

        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/sign-in", "POST"));

        filter.setAuthenticationSuccessHandler(authSuccessHandler());
        filter.setAuthenticationFailureHandler(authFailureHandler());

        // check authenticationManager is set
        filter.afterPropertiesSet();

        return filter;
    }

     @Bean
     public CorsFilter corsFilter() {
         var source = new UrlBasedCorsConfigurationSource();
         var config = new CorsConfiguration();
         config.setAllowedOrigins(List.of("*", "http://localhost:3010"));
         config.addAllowedMethod("*");
         config.addAllowedHeader("*");
         config.setAllowCredentials(true);
         source.registerCorsConfiguration("/**", config);
         return new CorsFilter(source);
     }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return  new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(accountFindService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/error")                  // spring boot default error handler
                .antMatchers("/css/**", "/js/**", "/img/**")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(corsFilter())
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .httpBasic()
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/account").permitAll()
                    .antMatchers(HttpMethod.GET, "/book/**", "/books/**").permitAll()
                    .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler())
                    .accessDeniedHandler(authDeniedHandler())
            .and()
                .formLogin()
                    .loginProcessingUrl("/auth/sign-in")
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/auth/sign-out")
                    .logoutSuccessHandler(logoutSuccessHandler())
                    .deleteCookies("JSESSIONID", "SPRING_SECURITY_REMEMBER_ME_COOKIE")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
            .and()
                .rememberMe()
                    .key("PUBLIC_SHARE_SECRET_KEY")
                    .authenticationSuccessHandler(authSuccessHandler())
                    .tokenValiditySeconds(7 * 24 * 60 * 60) // 1 week
            .and()
                .sessionManagement();
    }

}