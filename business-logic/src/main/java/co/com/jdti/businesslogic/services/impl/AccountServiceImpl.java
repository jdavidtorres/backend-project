package co.com.jdti.businesslogic.services.impl;

import co.com.jdti.businesslogic.dtos.AccountDTO;
import co.com.jdti.businesslogic.repositories.IAccountRepository;
import co.com.jdti.businesslogic.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

	private final IAccountRepository accountRepository;

	@Override
	public List<AccountDTO> getMyAccounts() {
		return List.of();
	}
}
