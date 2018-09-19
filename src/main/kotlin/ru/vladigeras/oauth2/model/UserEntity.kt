package ru.vladigeras.oauth2.model

import javax.persistence.*

/**
 * @author vladi_geras on 15.09.2018
 */
@Entity
@Table(name = "users")
open class UserEntity : LongIdentifiableEntity() {

	@Column(name = "login")
	var login: String? = null

	@Column(name = "password")
	var password: String? = null

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_role",
			joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)],
			uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("user_id", "role"))])
	@Enumerated(value = EnumType.STRING)
	@Column(name = "role")
	var roles: MutableSet<Role>? = null

}
