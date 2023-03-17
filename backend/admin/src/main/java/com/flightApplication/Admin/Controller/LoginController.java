package com.flightApplication.Admin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightApplication.Admin.Exception.CustomException;
import com.flightApplication.Admin.security.JWTRequestDTO;
import com.flightApplication.Admin.security.JWTResponse;
import com.flightApplication.Admin.security.JWTUserDetailsService;
import com.flightApplication.Admin.security.JwtTokenUtil;


@RestController
@RequestMapping("/admin/v1.0/api")
public class LoginController {

	@Autowired
	AuthenticationManager authManger;
	
	@Autowired
	JWTUserDetailsService userDeatilsService;
	
	@Autowired
	JwtTokenUtil JwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?>  createAuthenticationToken(@RequestBody JWTRequestDTO requestBody) throws Exception{
			System.out.println("its Reached here");
			System.out.println("checking");
			authenticate(requestBody.getUsername(),requestBody.getPassword());
			UserDetails loadUserByUsername = userDeatilsService.loadUserByUsername(requestBody.getUsername());
			String JwtToken = JwtUtil.generateToken(loadUserByUsername);
			return ResponseEntity.ok(new JWTResponse(JwtToken));
	}

	private void authenticate(String username, String password) throws Exception{
		try {
			authManger.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (DisabledException e) {
			throw new CustomException("USER_DISABLED", HttpStatus.UNAUTHORIZED);
		} catch (BadCredentialsException e) {
			throw new CustomException("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
		}
	}
	
}
