package co.com.jdti.businesslogic.controllers;

import co.com.jdti.businesslogic.dtos.AccountDTO;
import co.com.jdti.businesslogic.services.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

	private final IAccountService accountService;

	@GetMapping
	public List<AccountDTO> getMyAccounts() {
		return accountService.getMyAccounts();
	}
}
