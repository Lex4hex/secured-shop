package com.lex4hex.securedShop.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  private static String REALM = "shop";

  private final BCryptPasswordEncoder passwordEncoder;

  private final TokenStore tokenStore;

  private final UserApprovalHandler userApprovalHandler;

  private final AuthenticationManager authenticationManager;

  private final DataSource dataSource;

  @Autowired
  public AuthorizationServerConfiguration(BCryptPasswordEncoder passwordEncoder,
      TokenStore tokenStore,
      UserApprovalHandler userApprovalHandler, DataSource dataSource,
      @Qualifier("authenticationManagerBean")
          AuthenticationManager authenticationManager) {
    this.passwordEncoder = passwordEncoder;
    this.tokenStore = tokenStore;
    this.userApprovalHandler = userApprovalHandler;
    this.authenticationManager = authenticationManager;
    this.dataSource = dataSource;
  }

  @Bean
  protected AuthorizationCodeServices authorizationCodeServices() {
    return new JdbcAuthorizationCodeServices(dataSource);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authorizationCodeServices(authorizationCodeServices()).tokenStore(tokenStore)
        .userApprovalHandler(userApprovalHandler)
        .authenticationManager(authenticationManager);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.realm(REALM + "/client");
    oauthServer.passwordEncoder(passwordEncoder);
  }

}