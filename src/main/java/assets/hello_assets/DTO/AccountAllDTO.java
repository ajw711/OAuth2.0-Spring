package assets.hello_assets.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountAllDTO {

    private int accountNumber;

    private int balance;

    private float interestRate;

    private int termMonth;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String accountType;

    private String customerName;
}
