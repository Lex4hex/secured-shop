package com.lex4hex.securedShop.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lex4hex.securedShop")
//@EnableOAuth2Sso
public class ShopConfiguration extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ShopConfiguration.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ShopConfiguration.class);
  }
}
