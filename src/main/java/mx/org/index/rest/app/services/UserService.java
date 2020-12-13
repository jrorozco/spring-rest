package mx.org.index.rest.app.services;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.org.index.rest.app.entities.User;
import mx.org.index.rest.app.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


	@Autowired
	private UserRepository repository;
	
	public Page<User> getAllUsers(int page, int size){
		
		return repository.findAll(PageRequest.of(page, size));
	}

	public User getUserById(Integer userId) {
		return repository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El Id %d no se encuentra ", userId)));
	}
	
	@Cacheable("users")
	public User getUserByUsername(String user) {
		LOG.info("Getting user by username {}", user);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return repository.findByUsername(user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El Username %s no se encuentra ", user)));
	}
	
	public User getUserByUserNameAndpassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El usuario %s y el password %s no coincide",username,password)));
	}
	
	public Page<String> getUsersNames(int page, int size){
		return repository.findByUsernames(PageRequest.of(page, size));
	}
	
	@CacheEvict("users")
	public void deleteUserByUsername(String username) {
		User user = getUserByUsername(username);
		repository.delete(user);
	}
	
}
