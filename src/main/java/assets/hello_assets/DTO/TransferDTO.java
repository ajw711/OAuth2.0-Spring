package assets.hello_assets.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDTO {

    private int fromAccountNumber;

    private int toAccountNumber;

    private int amount;

    private int password;
}
