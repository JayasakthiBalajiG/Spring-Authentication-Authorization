package com.example.demo.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/v1")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserRestController {
	
	@Autowired
	UserService userService;

	 /**
        * This endpoint is responsible for restricting the ADMIN to view
        * URI: /v1/users
        * HTTP method: GET
    	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
		
	}

	 /**
        * This endpoint is responsible for adding the users
        * URI: /v1/users
        * HTTP method: POST
    	 */
	@PostMapping("/users")
	public ResponseEntity<User> saveusers(@RequestBody User newUser,Authentication auth) {
		System.out.println(newUser.getUserName()+"  "+auth.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body((userService.saveUser(newUser)));
		
	}

	 /**
        * This endpoint is responsible for viewing the users respective to their userId
        * URI: /v1/users/{userId}
        * HTTP method: GET
    	 */
	@PreAuthorize("@userSecurity.hasUserId(authentication,#userId)")
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId, Authentication authentication) {
		System.out.println("Ifnside getuserbyid method");
		return ResponseEntity.ok().body(userService.findUserById(userId).get());
		
	}


	 /**
        * This endpoint is responsible for deleting the users respective to their id
        * URI: /v1/users/{userId}
        * HTTP method: DELETE
    	 */
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") int UserId) {
		 userService.deleteUser(UserId);
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
}
