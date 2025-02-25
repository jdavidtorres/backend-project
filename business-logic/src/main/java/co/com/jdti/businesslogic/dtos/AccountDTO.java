package co.com.jdti.businesslogic.dtos;

public record AccountDTO(
	String id,
	String name,
	double balance,
	String accountNumber
) {
}
