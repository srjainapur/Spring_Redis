package com.java.cache.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.cache.bean.User;
import com.java.cache.repository.UserRepository;

@RestController
public class RedisUserController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User save = userRepository.save(user);
		return new ResponseEntity<User>(save, HttpStatus.CREATED);
	}
	
	@Cacheable(value="users", key="#userId", unless="#result.follower < 1000")
	@RequestMapping(value="/getUserById/{userId}", method=RequestMethod.GET)
	public User getUser(@PathVariable("userId") Integer userId) {
		LOG.info("Getting user with ID {}.", userId);
		Optional<User> findById = userRepository.findById(userId);
		return findById.get();
	}
	
	@CachePut(key="#userId", value="users")
	@RequestMapping(value="/updateUser/{userId}", method=RequestMethod.PUT)
	public User updateUser(@RequestBody User user, @PathVariable("userId") Integer userId) {
		LOG.info("Updating the user with uderId ", userId);
		
		User oldUser = userRepository.findById(userId).get();
		oldUser.setFollower(user.getFollower());
		oldUser.setName(user.getName());
		
		User savedUser = userRepository.save(oldUser);
		return savedUser;
	}
	
	@CacheEvict(value="users", allEntries=true)
	@RequestMapping(value="/deleteUser/{userId}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") Integer userId) {
		userRepository.deleteById(userId);
	}
	
	
}
