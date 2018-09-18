package ru.vladigeras.oauth2.service

import ru.vladigeras.oauth2.model.UserEntity

/**
 * @author vladi_geras on 15.09.2018
 */
interface UserService {
	fun findUserByLogin(login: String): UserEntity?
}