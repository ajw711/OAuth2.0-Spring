package assets.hello_assets.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Analysis extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="analysis_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;


    private Long depositCount;

    private Long transferCount;

    private int totalDeposit;

    private int totalTransfer;

    private int maxDeposit;

    private int minDeposit;

    private int maxTransfer;

    private int minTransfer;

    private double avgDeposit;

    private double avgTransfer;

}
