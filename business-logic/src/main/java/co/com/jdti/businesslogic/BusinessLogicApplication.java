package co.com.jdti.businesslogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"co.com.jdti.coresecurity.entities", "co.com.jdti.businesslogic.entities"})
@ComponentScan(basePackages = {"co.com.jdti.coresecurity", "co.com.jdti.businesslogic"})
@EnableJpaRepositories(basePackages = {"co.com.jdti.coresecurity.repositories", "co.com.jdti.businesslogic.repositories"})
public class BusinessLogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessLogicApplication.class, args);
	}
}
