package assets.hello_assets.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transcation_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="from_account_id")
    private Account fromAccount; // 출금 계좌

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="to_account_id")
    private Account toAccount; // 입금 계좌

    private int amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


}
