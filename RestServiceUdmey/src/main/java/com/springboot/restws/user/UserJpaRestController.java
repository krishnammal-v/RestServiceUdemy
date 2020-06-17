
package com.springboot.restws.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
public class UserJpaRestController {

	@Autowired
	private UserDAO userDaoService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	
	@GetMapping(path="/jpa/users")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public Resource<User> findUser(@PathVariable Integer id){
		
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent())
			throw new UserException("The user you are searching is not found");
		
		//HATEOAS
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("linkToAllUsers"));
		
		
		//HATEOAS
		return resource;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity addUser(@Valid @RequestBody User user){
		User createdStatus = userRepo.saveAndFlush(user);
		
		if(createdStatus != null){
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		else{
			throw new UserException("User with same id already exists.");
		}
		
	}
	
	@PostMapping(path="/jpa/users/{id}")
	public void createPost(@PathVariable Integer id,@RequestBody Post post){
		
		
		Optional<User> user = userRepo.findById(id);
		
		if(!user.isPresent()){
			
			throw new UserException("User not found");
		}
		
		post.setUser(user.get());
		postRepo.save(post);
		
	}
}