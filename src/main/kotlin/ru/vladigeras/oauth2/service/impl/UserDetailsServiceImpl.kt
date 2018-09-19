package ru.vladigeras.oauth2.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vladigeras.oauth2.model.Role
import ru.vladigeras.oauth2.repository.UserRepository


/**
 * @author vladi_geras on 19.09.2018
 */
@Service(value = "userDetailsService")
class UserDetailsServiceImpl : UserDetailsService {

	@Autowired
	private val userRepository: UserRepository? = null

	@Transactional
	@Throws(UsernameNotFoundException::class)
	override fun loadUserByUsername(userId: String): UserDetails {
		val user = userRepository?.findByLogin(userId)
				?: throw UsernameNotFoundException("Invalid username or password.")
		return User(user.login, user.password, user.roles?.map { role: Role -> SimpleGrantedAuthority(role.name) })
	}
}