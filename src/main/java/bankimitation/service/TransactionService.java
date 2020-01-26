package bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankimitation.dao.GenericDAO;
import bankimitation.model.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService {
    private GenericDAO<Transaction> transactionDAO;

    @Autowired
    public void setTransactionDAO(GenericDAO<Transaction> transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> getAllByClient(int id, Date after, Date before) {
        List<Transaction> transactions = transactionDAO.getAll().stream()
        								 .filter(t -> t.getClient().getId() == id)
        								 .filter(temp -> temp.getDate().after(after) && temp.getDate().before(before))
        								 .collect(Collectors.toList());
        return transactions;
    }

    public List<Transaction> getAllByAccount(int id) {
    	List<Transaction> transactions = transactionDAO.getAll().stream()
										.filter(t-> t.getAccount() != null && t.getAccount().getId() == id)
										.collect(Collectors.toList());
        return transactions;
    }

    public void add(Transaction transaction) {
        transactionDAO.add(transaction);
    }

    public void delete(Transaction transaction) {
        transactionDAO.delete(transaction);
    }

    public Transaction getById(int id) {
        return transactionDAO.getById(id);
    }
}
