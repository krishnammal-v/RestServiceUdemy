package com.springboot.restws.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.core.IsNull;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	
	private static Set<User> users = new HashSet<User>();
	private static int idCount = 3;
	static{
		users.add(new User(1,"aaa",new Date()));
		users.add(new User(2,"bbb",new Date()));
		users.add(new User(3,"ccc",new Date()));
	}
	
	public Set<User> getAllUsers(){
		return users;
	}

	public boolean addUser(User user)
	{
		if(user.getId() == null ){
			++idCount;
			user.setId(idCount);
		}else{
			idCount = user.getId();
		}
		return users.add(user);
	}
	
	public User findUser(Integer id){
		
		for(User user:users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
}
