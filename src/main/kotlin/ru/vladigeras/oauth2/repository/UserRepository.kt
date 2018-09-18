package ru.vladigeras.oauth2.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.vladigeras.oauth2.model.UserEntity

/**
 * @author vladi_geras on 15.09.2018
 */
@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
	fun findByLogin(login: String): UserEntity?
}