package com.example.fitbull.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.AuthenticationException;
import com.example.fitbull.entities.User;
import com.example.fitbull.request.UserRequest;
import com.example.fitbull.response.AuthResponse;
import com.example.fitbull.security.JwtTokenProvider;
import com.example.fitbull.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private AuthenticationManager authenticationManager;

	private JwtTokenProvider jwtTokenProvider;

	private UserService userService;

	private PasswordEncoder passwordEncoder;

	public AuthController(AuthenticationManager authenticationManager, UserService userService,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@GetMapping("/{userId}")
	public Object getOneUser(@PathVariable Long userId) {
		return userService.getOneUserById(userId);
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserRequest loginRequest) {

		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					loginRequest.getEmail(), loginRequest.getPassword());
			User user = userService.getOneUserByEmail(loginRequest.getEmail());
			Authentication auth = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
			String jwtToken = jwtTokenProvider.generateJwtToken(auth);
			AuthResponse authResponse = new AuthResponse();
			authResponse.setMessage("Login succesfully.");
			authResponse.setAccessToken("Bearer " + jwtToken);
			authResponse.setUserId(user.getId());
			System.out.println(authResponse);
			return authResponse;

		} catch (BadCredentialsException ex) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect email or password.");
		} catch (UsernameNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not registered.");
		} catch (AuthenticationException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication failed.");
		}

	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) {
		AuthResponse authResponse = new AuthResponse();
		if (userService.getOneUserByEmail(registerRequest.getEmail()) != null) {
			authResponse.setMessage("Email already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userService.saveOneUser(user);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				registerRequest.getEmail(), registerRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);

		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setUserId(user.getId());
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteById(userId);
	}

}
