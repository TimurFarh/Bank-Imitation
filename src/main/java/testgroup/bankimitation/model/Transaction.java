package testgroup.bankimitation.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Operations operation;

    @Column(name = "account_from")
    private String from;

    @Column(name = "account_to")
    private String to;
    private int amount;

    @Column(name = "date_time")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @Nullable
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @Nullable
    private Client client;

    public Transaction() {
    }

    public Transaction(Operations operation, String from, String to, int amount, @Nullable Account account, Client client) {
        this.operation = operation;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.account = account;
        this.client = client;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    @Nullable
    public Client getClient() {
        return client;
    }

    public void setClient(@Nullable Client client) {
        this.client = client;
    }
}
