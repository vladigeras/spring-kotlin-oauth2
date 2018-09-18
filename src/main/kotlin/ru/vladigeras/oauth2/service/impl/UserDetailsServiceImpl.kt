package ru.vladigeras.oauth2.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vladigeras.oauth2.model.CustomUserDetails
import ru.vladigeras.oauth2.repository.UserRepository

/**
 * @author vladi_geras on 18.09.2018
 */
@Service
class UserDetailsServiceImpl(@Autowired private val userRepository: UserRepository) : UserDetailsService {

	@Transactional
	override fun loadUserByUsername(username: String): UserDetails {
		val user = userRepository.findByLogin(username)
				?: throw UsernameNotFoundException("Username $username is not found")

		return CustomUserDetails(user)
	}
}