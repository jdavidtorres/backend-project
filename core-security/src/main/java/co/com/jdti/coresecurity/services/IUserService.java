package co.com.jdti.coresecurity.services;

import co.com.jdti.coresecurity.dtos.LoginRequestDTO;
import co.com.jdti.coresecurity.dtos.LoginResponseDTO;
import co.com.jdti.coresecurity.dtos.RegisterRequestDTO;
import co.com.jdti.coresecurity.dtos.RegisterResponseDTO;

public interface IUserService {

	LoginResponseDTO authenticate(LoginRequestDTO loginRequest);

	RegisterResponseDTO register(RegisterRequestDTO registerRequest);

	String getCurrentUsername();

	String getCurrentUserId();
}
