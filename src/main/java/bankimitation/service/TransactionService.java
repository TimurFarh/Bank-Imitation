package bankimitation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankimitation.dao.TransactionDao;
import bankimitation.model.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService extends AbstractService<Transaction>{

	/**
	 * Метод получения всех операций, совершенных клиентом в определенный
	 * промежуток времени.
	 * @param id номер клиента.
	 * @param after дата начала рассматриваемого промежутка.
	 * @param before дата окончания рассматриваемого промежутка.
	 * @return список операций клиента.
	 */
	public List<Transaction> getAllByClient(int id, Date after, Date before) {
		List<Transaction> transactions = getDao().getAll().stream()
				.filter(t -> t.getClient().getId() == id)
				.filter(temp -> temp.getDate().after(after) && temp.getDate().before(before))
				.collect(Collectors.toList());
		return transactions;
	}

	/**
	 * Метод получения всех операций, совершенных по определенному счёту.
	 * @param id номер счёта.
	 * @return список операция по счёту.
	 */
	public List<Transaction> getAllByAccount(int id) {
		List<Transaction> transactions = getDao().getAll().stream()
				.filter(t -> t.getAccount() != null && t.getAccount().getId() == id)
				.collect(Collectors.toList());
		return transactions;
	}
}
