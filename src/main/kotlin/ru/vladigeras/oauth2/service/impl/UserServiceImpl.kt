package ru.vladigeras.oauth2.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vladigeras.oauth2.model.UserEntity
import ru.vladigeras.oauth2.repository.UserRepository
import ru.vladigeras.oauth2.service.UserService

/**
 * @author vladi_geras on 15.09.2018
 */
@Service
class UserServiceImpl : UserService {

	@Autowired
	private val userRepository: UserRepository? = null

	@Transactional
	override fun findUserByLogin(login: String): UserEntity? {
		return userRepository?.findByLogin(login)
	}
}