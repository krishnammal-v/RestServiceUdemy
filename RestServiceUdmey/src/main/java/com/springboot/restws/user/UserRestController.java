
package com.springboot.restws.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.aspectj.weaver.AjAttribute.MethodDeclarationLineNumberAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserRestController {

	@Autowired
	private UserDAO userDaoService;
	
	
	@GetMapping(path="/users")
	public Set<User> getAllUsers(){
		return userDaoService.getAllUsers();
	}
	
	@GetMapping(path="/users/{id}")
	public Resource<User> findUser(@PathVariable Integer id){
		
		User user = userDaoService.findUser(id);
		if(user == null)
			throw new UserException("The user you are searching is not found");
		
		//HATEOAS
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("linkToAllUsers"));
		
		
		//HATEOAS
		return resource;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity addUser(@Valid @RequestBody User user){
		boolean createdStatus = userDaoService.addUser(user);
		
		if(createdStatus){
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		else{
			throw new UserException("User with same id already exists.");
		}
		
	}
}