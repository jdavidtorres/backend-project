package co.com.jdti.coresecurity.services.impl;

import co.com.jdti.coresecurity.repositories.IUserRepository;
import co.com.jdti.coresecurity.services.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

	private final IUserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Loading user by username: {}", username);
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
