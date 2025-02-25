package co.com.jdti.businesslogic.services;

import co.com.jdti.businesslogic.dtos.AccountDTO;

import java.util.List;

public interface IAccountService {

	List<AccountDTO> getMyAccounts();
}
