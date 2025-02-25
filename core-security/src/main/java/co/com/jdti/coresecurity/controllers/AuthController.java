package co.com.jdti.coresecurity.controllers;

import co.com.jdti.coresecurity.dtos.LoginRequestDTO;
import co.com.jdti.coresecurity.dtos.LoginResponseDTO;
import co.com.jdti.coresecurity.dtos.RegisterRequestDTO;
import co.com.jdti.coresecurity.dtos.RegisterResponseDTO;
import co.com.jdti.coresecurity.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final IUserService userService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
		System.out.println("loginRequest: " + loginRequest);
		return ResponseEntity.ok(userService.authenticate(loginRequest));
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
		System.out.println("registerRequest: " + registerRequest);
		return ResponseEntity.ok(userService.register(registerRequest));
	}
}
