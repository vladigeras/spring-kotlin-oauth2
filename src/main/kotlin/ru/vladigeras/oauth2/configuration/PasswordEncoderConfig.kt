package ru.vladigeras.oauth2.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * @author vladi_geras on 18.09.2018
 */
@Configuration
class PasswordEncoderConfig {

	@Bean
	fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}