package ru.vladigeras.oauth2.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

/**
 * @author vladi_geras on 18.09.2018
 */
class CustomUserDetails(val userEntity: UserEntity) : UserDetails {

	override fun getAuthorities(): Collection<GrantedAuthority> {
		return userEntity.roles
				?.stream()
				?.map { role ->
					SimpleGrantedAuthority(role.toString())
				}
				?.collect(Collectors.toList())!!
	}

	override fun getPassword(): String {
		return userEntity.password!!
	}

	override fun getUsername(): String {
		return userEntity.login!!
	}

	override fun isEnabled(): Boolean {
		return true
	}

	override fun isCredentialsNonExpired(): Boolean {
		return true
	}

	override fun isAccountNonExpired(): Boolean {
		return true
	}

	override fun isAccountNonLocked(): Boolean {
		return true
	}
}