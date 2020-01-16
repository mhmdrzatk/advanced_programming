package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.test.User;
import com.example.demo.test.UserRepository;
@RestController // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UserController {
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
private UserRepository userRepository;

@PostMapping(path="/add") // Map ONLY POST Requests
public @ResponseBody String addNewUser (@RequestBody User user) {
// @ResponseBody means the returned String is the response, not a view name
// @RequestParam means it is a parameter from the GET or POST request
	if(userRepository.findByUserName(user.getUserName())) {
		return "Username is taken";
	}
	else {
		userRepository.save(user);
		
	}
	return "save";
}

@GetMapping(path="/all")
public @ResponseBody Iterable<User> getAllUsers() {
// This returns a JSON or XML with the users
return userRepository.findAll();
}
}