package ru.vladigeras.oauth2.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author vladi_geras on 15.09.2018
 */
@RestController
@RequestMapping(value = ["/test"])
class TestController {

	@GetMapping(value = ["/message"])
	fun getMessage(): String {
		return "Successful test message"
	}
}