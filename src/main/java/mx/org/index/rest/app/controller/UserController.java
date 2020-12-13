package mx.org.index.rest.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import mx.org.index.rest.app.entities.User;
import mx.org.index.rest.app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;


	@GetMapping
	@Timed("get.users")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(required=false, value="page",defaultValue = "0") int page,@RequestParam(required=false, value="size",defaultValue = "1000")  int size){
		return new ResponseEntity<Page<User>>(service.getAllUsers(page,size) ,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId ){
		return new ResponseEntity<User>(service.getUserById(userId),HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username ){
		return new ResponseEntity<User>( service.getUserByUsername(username),HttpStatus.OK);
	}
	
	
	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUserNames(@RequestParam(required=false, value="page",defaultValue = "0") int page,@RequestParam(required=false, value="size",defaultValue = "1000")  int size){
		return new ResponseEntity<Page<String>>(service.getUsersNames(page, size) ,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> getUserByUsernameAndPassword(@RequestBody User user  ){
		return new ResponseEntity<User>(service.getUserByUserNameAndpassword(user.getUsername(), user.getPassword()) ,HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username ){
		service.deleteUserByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
