package com.lex4hex.securedShop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "shop_rest_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    /**
     * Configure access to rest actions
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/shop/cart/{\\d+}/add/{\\d+}").permitAll() // Add product to customer
                .antMatchers("/api/shop/cart/customer/{\\d+}").permitAll() // Create cart
                .antMatchers("/api/shop/customer/{\\d+}/order").permitAll() // Create order
                .antMatchers("/api/shop/products").permitAll() // Get all products
                .antMatchers("/api/shop/**").access("hasRole('ADMIN')") // All other actions require admin role
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}