package co.com.jdti.businesslogic.repositories;

import co.com.jdti.businesslogic.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, String> {

	List<AccountEntity> findByUserId(String userId);
}
