package co.com.jdti.businesslogic;

import co.com.jdti.businesslogic.entities.AccountEntity;
import co.com.jdti.businesslogic.repositories.IAccountRepository;
import co.com.jdti.coresecurity.dtos.RegisterRequestDTO;
import co.com.jdti.coresecurity.dtos.RegisterResponseDTO;
import co.com.jdti.coresecurity.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
@EntityScan(basePackages = {"co.com.jdti.coresecurity.entities", "co.com.jdti.businesslogic.entities"})
@ComponentScan(basePackages = {"co.com.jdti.coresecurity", "co.com.jdti.businesslogic"})
@EnableJpaRepositories(basePackages = {"co.com.jdti.coresecurity.repositories", "co.com.jdti.businesslogic.repositories"})
@RequiredArgsConstructor
public class BusinessLogicApplication implements CommandLineRunner {

	private final IUserService userService;
	private final IAccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(BusinessLogicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RegisterResponseDTO user = userService.register(new RegisterRequestDTO("pepe@lepiu.com", "12345678"));

		accountRepository.save(AccountEntity.builder()
			.name("Principal")
			.balance(new BigDecimal("250.00"))
			.accountNumber("12345")
			.userId(user.id())
			.build());
	}
}
