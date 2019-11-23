package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.TransactionDAO;
import testgroup.bankimitation.model.Client;
import testgroup.bankimitation.model.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    private TransactionDAO transactionDAO;

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> getAllByClient(Client client, Date after, Date before) {
        List<Transaction> result = new ArrayList<>();
        client.getAccounts().stream().
                map(account -> getAllByAccount(account.getId(), after, before)).
                map(result::addAll);
        return result;
    }

    public List<Transaction> getAllByAccount(int id, Date after, Date before) {
        List<Transaction> transactions = transactionDAO.getAll(id);
        Iterator<Transaction> it = transactions.iterator();
        while (it.hasNext()) {
            Transaction temp = it.next();
            if (!(temp.getDate().after(after) && temp.getDate().before(before))) transactions.remove(temp);
        }
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
