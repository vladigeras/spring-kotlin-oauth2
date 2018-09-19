package ru.vladigeras.oauth2.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore

/**
 * @author vladi_geras on 19.09.2018
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

	val CLIENT_ID = "client"

	// this is a 'secret' in BCrypt form
	val CLIENT_SECRET = "\$2a\$04\$DLBoHaALyDncyAYkhKwh0.mPO3RDaDvFA5nXLyUDvwdqWM93O.TgS"

	val GRANT_TYPE_PASSWORD = "password"
	val AUTHORIZATION_CODE = "authorization_code"
	val REFRESH_TOKEN = "refresh_token"
	val IMPLICIT = "implicit"
	val SCOPE_READ = "read"
	val SCOPE_WRITE = "write"
	val TRUST = "trust"
	val ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60
	val REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60

	@Autowired
	private val tokenStore: TokenStore? = null

	@Autowired
	private val authenticationManager: AuthenticationManager? = null

	@Throws(Exception::class)
	override fun configure(configurer: ClientDetailsServiceConfigurer) {
		configurer
				.inMemory()
				.withClient(CLIENT_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
	}

	@Throws(Exception::class)
	override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
	}

}
