package co.com.jdti.coresecurity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
	@NotBlank String username,
	@NotBlank @Size(min = 8, max = 20) String password
) {
}
