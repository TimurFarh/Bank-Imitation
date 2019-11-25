package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.TransactionDAO;
import testgroup.bankimitation.model.Transaction;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    private TransactionDAO transactionDAO;

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> getAllByClient(int id, Date after, Date before) {
        List<Transaction> transactions = transactionDAO.getAllByClient(id);
        transactions.removeIf(temp -> !(temp.getDate().after(after) && temp.getDate().before(before)));
        return transactions;
    }

    public List<Transaction> getAllByAccount(int id) {
        List<Transaction> transactions = transactionDAO.getAllByAccount(id);
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
