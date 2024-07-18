package assets.hello_assets.DTO;

import assets.hello_assets.domain.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class AccountDetailDTO {

    private int accountNumber;
    private int balance;
    private String customerName;
    private LocalDateTime createDate;
    private AccountType accountType;

}
