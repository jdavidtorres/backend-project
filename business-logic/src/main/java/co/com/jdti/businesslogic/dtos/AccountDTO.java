package co.com.jdti.businesslogic.dtos;

import java.math.BigDecimal;

public record AccountDTO(
	String id,
	String name,
	BigDecimal balance,
	String accountNumber
) {
}
