package co.com.jdti.businesslogic.services.impl;

import co.com.jdti.businesslogic.dtos.AccountDTO;
import co.com.jdti.businesslogic.entities.AccountEntity;
import co.com.jdti.businesslogic.repositories.IAccountRepository;
import co.com.jdti.businesslogic.services.IAccountService;
import co.com.jdti.coresecurity.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

	private final IAccountRepository accountRepository;
	private final IUserService userService;

	@Override
	public List<AccountDTO> getMyAccounts() {
		List<AccountEntity> accounts = accountRepository.findByUserId(userService.getCurrentUserId());
		return accounts.stream().map(account -> new AccountDTO(
			account.getId(),
			account.getName(),
			account.getBalance(),
			account.getAccountNumber()
		)).toList();
	}
}
