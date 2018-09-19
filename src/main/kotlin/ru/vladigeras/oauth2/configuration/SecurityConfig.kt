package ru.vladigeras.oauth2.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


/**
 * @author vladi_geras on 19.09.2018
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

	@Autowired
	@Qualifier(value = "userDetailsService")
	private val userDetailsService: UserDetailsService? = null

	@Bean
	@Throws(Exception::class)
	override fun authenticationManagerBean(): AuthenticationManager {
		return super.authenticationManagerBean()
	}

	@Autowired
	@Throws(Exception::class)
	fun globalUserDetails(auth: AuthenticationManagerBuilder) {
		auth.userDetailsService<UserDetailsService>(userDetailsService)
				.passwordEncoder(encoder())
	}

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http
				.csrf().disable()
				.anonymous().disable()
				.authorizeRequests()
				.antMatchers("/api-docs/**").permitAll()
	}

	@Bean
	fun tokenStore(): TokenStore {
		return InMemoryTokenStore()
	}

	@Bean
	fun encoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}

	@Bean
	fun corsFilter(): FilterRegistrationBean<*> {
		val source = UrlBasedCorsConfigurationSource()
		val config = CorsConfiguration()
		config.allowCredentials = true
		config.addAllowedOrigin("*")
		config.addAllowedHeader("*")
		config.addAllowedMethod("*")
		source.registerCorsConfiguration("/**", config)
		val bean = FilterRegistrationBean(CorsFilter(source))
		bean.order = 0
		return bean
	}
}
