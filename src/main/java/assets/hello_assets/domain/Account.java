package assets.hello_assets.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Account extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private int accountNumber;

    private int password;

    private int balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private int termMonth;

    private float interestRate;

    @OneToMany(mappedBy = "toAccount", fetch = FetchType.LAZY)
    private List<Transaction> toAccountList;

    @OneToMany(mappedBy = "fromAccount", fetch = FetchType.LAZY)
    private List<Transaction> fromAccountList;



}
