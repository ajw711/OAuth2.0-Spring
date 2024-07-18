package assets.hello_assets.DTO;

import assets.hello_assets.domain.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSummaryDTO {


    private int accountNumber;

    private int balance;

    private AccountType accountType;



}
