package testgroup.bankimitation.model;

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
    private BankAccount account;

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

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
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
}
