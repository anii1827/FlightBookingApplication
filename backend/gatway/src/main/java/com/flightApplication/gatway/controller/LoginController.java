//package com.flightApplication.gatway.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.flightApplication.gatway.dto.JWTRequestDTO;
//import com.flightApplication.gatway.dto.JWTResponse;
//import com.flightApplication.gatway.security.JWTUserDetailsService;
//import com.flightApplication.gatway.util.JwtTokenUtil;
//
//
//
//@RestController
//@RequestMapping("/api/gatway")
//public class LoginController {
//
//	@Autowired
//	AuthenticationManager authManger;
//	
//	@Autowired
//	JWTUserDetailsService userDeatilsService;
//	
//	@Autowired
//	JwtTokenUtil JwtUtil;
//	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?>  createAuthenticationToken(@RequestBody JWTRequestDTO requestBody) throws Exception{
//			System.out.println("checking");
//			authenticate(requestBody.getUsername(),requestBody.getPassword());
//			UserDetails loadUserByUsername = userDeatilsService.loadUserByUsername(requestBody.getUsername());
//			String JwtToken = JwtUtil.generateToken(loadUserByUsername);
//			return ResponseEntity.ok(new JWTResponse(JwtToken));
//	}
//
//	private void authenticate(String username, String password) throws Exception{
//		try {
//			authManger.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		}
//		catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
//	
//}
