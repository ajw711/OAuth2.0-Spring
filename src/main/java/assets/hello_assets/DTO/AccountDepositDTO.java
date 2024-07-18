package assets.hello_assets.DTO;

import assets.hello_assets.domain.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDepositDTO {

    private AccountType accountType;

    private int password;

    private int balance;

    private int termMonth;

    private float interestRate;

}
