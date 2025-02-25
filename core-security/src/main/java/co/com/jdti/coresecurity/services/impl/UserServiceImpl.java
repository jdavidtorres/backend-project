package co.com.jdti.coresecurity.services.impl;

import co.com.jdti.coresecurity.dtos.LoginRequestDTO;
import co.com.jdti.coresecurity.dtos.LoginResponseDTO;
import co.com.jdti.coresecurity.dtos.RegisterRequestDTO;
import co.com.jdti.coresecurity.dtos.RegisterResponseDTO;
import co.com.jdti.coresecurity.entities.UserEntity;
import co.com.jdti.coresecurity.repositories.IUserRepository;
import co.com.jdti.coresecurity.services.IUserService;
import co.com.jdti.coresecurity.shared.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final IUserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final TokenManager tokenManager;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Loading user by username: {}", username);
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Override
	public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
		try {
			var authToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
			var authentication = authenticationManager.authenticate(authToken);

			LoginResponseDTO loginResponse = new LoginResponseDTO(loginRequest.username(), tokenManager.generateToken((UserDetails) authentication.getPrincipal()));
			log.debug("Authenticated user {}", loginRequest.username());
			return loginResponse;
		} catch (BadCredentialsException badCredException) {
			log.warn("Invalid username or password for user {}", loginRequest.username());
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	@Override
	public RegisterResponseDTO register(RegisterRequestDTO registerRequest) {
		if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
			log.warn("Username {} already exists", registerRequest.username());
			throw new IllegalArgumentException("Username already exists");
		}

		String passwordEncoded = passwordEncoder.encode(registerRequest.password());

		UserEntity user = new UserEntity();
		user.setUsername(registerRequest.username());
		user.setPassword(passwordEncoded);
		userRepository.save(user);
		log.debug("Registered user {}", registerRequest.username());

		return new RegisterResponseDTO(user.getUsername(), tokenManager.generateToken(user));
	}
}
