package ru.vladigeras.oauth2.model

import javax.persistence.*

/**
 * @author vladi_geras on 15.09.2018
 */
@MappedSuperclass
open class LongIdentifiableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private var id: Long? = null
}