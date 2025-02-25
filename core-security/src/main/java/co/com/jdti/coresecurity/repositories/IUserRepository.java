package co.com.jdti.coresecurity.repositories;

import co.com.jdti.coresecurity.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByUsername(String username);
}
