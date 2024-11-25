package com.SpringSecurityRoles.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurityRoles.exception.domain.ExceptionHandlig;
import com.SpringSecurityRoles.exception.domain.UserNotFoundException;

@RestController
// @RequestMapping(value = "/user")
@RequestMapping(path = {"/", "user"})
public class UserResource extends ExceptionHandlig {

	@GetMapping("/home")
	public String showUser() throws UserNotFoundException {
		// return "Aplication works";
		throw new UserNotFoundException("El usuario no existe en nuestra base de datos");
	};
}
