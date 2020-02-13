package bankimitation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankimitation.error.AccountAlreadyExistsException;
import bankimitation.error.NotEnoughMoneyException;
import bankimitation.error.WrongAccountException;
import bankimitation.model.Account;
import bankimitation.model.Operations;
import bankimitation.model.Transaction;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService extends AbstractService<Account> {
	
	/**
	 * Метод получения всех счетов клиента
	 * @param id номер клиента
	 * @return список счетов клиента.
	 */
	public List<Account> getAll(int id) {
		List<Account> result = getDao().getAll().stream().filter(n -> n.getClient().getId() == id)
				.collect(Collectors.toList());
		return result;
	}

	/**
	 * Метод редактирования счета.
	 * @param account редактируемый счёт.
	 * @param transaction операция, совершаемая над счётом.
	 * @return описание в {@link #checkDepositAccount}.
	 * @throws NotEnoughMoneyException описание в {@link #withdraw}
	 * @throws WrongAccountException описание в {@link #checkDepositAccount} и в {@link #deposit}.
	 */
	public Transaction edit(Account account, Transaction transaction)
			throws NotEnoughMoneyException, WrongAccountException {
		Transaction depositTransaction = null;
		Operations operation = transaction.getOperation();
		switch (operation) {
		case Deposit:
			deposit(account, transaction);
			break;

		case Withdraw:
			depositTransaction = withdraw(account, transaction);
			break;

		case Close:
			if (account.getBalance() > 0) 
				depositTransaction = checkDepositAccount(account, transaction);
			getDao().delete(account);
			break;
		}
		return depositTransaction;
	}
	
	/**
	 * Метод добавляет новый счёт, если с таким названием ещё нет в списке.
	 * @param account новый счёт
	 * @param id номер клиента
	 * @throws AccountAlreadyExistsException если счёт с таким именем у клиента уже есть.
	 */
	public void add(Account account, int id) throws AccountAlreadyExistsException {
		List<Account> accounts = getAll(id);
		if (accounts.stream().noneMatch(a-> a.equals(account))) super.add(account);
		else throw new AccountAlreadyExistsException();
	}

	/**
	 * Метод зачисления денег на счёт.
	 * @param account счёт зачисления.
	 * @param transaction операция зачисления.
	 * @throws WrongAccountException если счёт списания содержит цифровое представление, т.к. зачислить 
	 * 		   можно только с внешнего счёта(должно быть название).
	 */
	private void deposit(Account account, Transaction transaction) throws WrongAccountException {
		if (transaction.getFrom().matches("\\d+")) throw new WrongAccountException();
		
		account.setBalance(account.getBalance() + transaction.getAmount());
		getDao().edit(account);
	}
	
	/**
	 * Метод списания денег со счёта.
	 * @param account счёт списания.
	 * @param transaction операция списания.
	 * @return описание в {@link #checkDepositAccount}.
	 * @throws WrongAccountException описание в {@link #checkDepositAccount}.
	 * @throws NotEnoughMoneyException если баланс счёта меньше списываемой суммы.
	 */
	private Transaction withdraw(Account account, Transaction transaction) throws WrongAccountException, NotEnoughMoneyException {
		if (account.getBalance() < transaction.getAmount())	throw new NotEnoughMoneyException();
		
		Transaction depositTransaction = checkDepositAccount(account, transaction);
		account.setBalance(account.getBalance() - transaction.getAmount());
		getDao().edit(account);
		return depositTransaction;
	}

	/**
	 * Метод для проверки счёта, на который пытаются перевести деньги при списании
	 * или удалении счета.
	 * @param account счёт, с которого списываются деньги, либо который закрывают.
	 * @param transaction операция списания или закрытия счёта.
	 * @return операцию пополнения счета, либо null, если перевод происходит на внешний счёт.
	 * @throws WrongAccountException:
	 * 	<ul>
	 * <li>если совершена попытка перевода на тот же самый счет.</li>
	 * <li>если введенный номер счёта не существует.</li>
	 * </ul>
	 */
	private Transaction checkDepositAccount(Account account, Transaction transaction) throws WrongAccountException {
		/*Если название счёта, на который переводят деньги, не содержит цифр, то этого счёта нет в этом банке
		и как следствие операция зачисления невозможна.*/
		if (!transaction.getTo().matches("\\d+")) return null;
		
		int depositNumber = Integer.parseInt(transaction.getTo());
		if (depositNumber == account.getId()) throw new WrongAccountException();
		
		Account depositAccount = getById(depositNumber);
		if (depositAccount == null)	throw new WrongAccountException();
		
		/* Здесь мы пополняем счёт и создаем операцию пополнения, беря всю необходимую информацию из операции 
		списания, либо закрытия, и счёта пополнения.*/
		depositAccount.setBalance(depositAccount.getBalance() + transaction.getAmount());
		Transaction depositTransaction = new Transaction(Operations.Deposit, transaction.getFrom(), transaction.getTo(),
				transaction.getAmount(), depositAccount, depositAccount.getClient());
		return depositTransaction;
	}
}
