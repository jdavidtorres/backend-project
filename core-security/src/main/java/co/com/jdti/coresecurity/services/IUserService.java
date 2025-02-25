package co.com.jdti.coresecurity.services;

import co.com.jdti.coresecurity.dtos.LoginRequestDTO;
import co.com.jdti.coresecurity.dtos.LoginResponseDTO;
import co.com.jdti.coresecurity.dtos.RegisterRequestDTO;
import co.com.jdti.coresecurity.dtos.RegisterResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

	LoginResponseDTO authenticate(LoginRequestDTO loginRequest);

	RegisterResponseDTO register(RegisterRequestDTO registerRequest);
}
