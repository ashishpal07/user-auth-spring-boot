package com.example.authService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@SpringBootTest
class AuthServiceApplicationTests {

	@Autowired
	private RegisteredClientRepository registeredClientRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	@Commit
//	public void registeredClientRepository() {
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("oidc-client")
//				.clientSecret("{noop}secret")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//				.postLogoutRedirectUri("http://127.0.0.1:8080/")
//				.scope(OidcScopes.OPENID)
//				.scope(OidcScopes.PROFILE)
//				.scope("ADMIN")
//				.scope("STUDENT")
//				.scope("MENTOR") // Role
//				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//				.build();
//
//		registeredClientRepository.save(oidcClient);
//	}

}
