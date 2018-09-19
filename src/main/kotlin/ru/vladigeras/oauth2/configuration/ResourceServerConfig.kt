package ru.vladigeras.oauth2.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler


/**
 * @author vladi_geras on 19.09.2018
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

	private val RESOURCE_ID = "resource_id"

	override fun configure(resources: ResourceServerSecurityConfigurer?) {
		resources!!.resourceId(RESOURCE_ID).stateless(false)
	}

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.anonymous().disable()
				.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
	}

}