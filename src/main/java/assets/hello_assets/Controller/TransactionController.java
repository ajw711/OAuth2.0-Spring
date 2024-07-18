package assets.hello_assets.Controller;

import assets.hello_assets.DTO.CustomOAuth2User;
import assets.hello_assets.DTO.DepositDTO;
import assets.hello_assets.DTO.TransferDTO;
import assets.hello_assets.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/transaction/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositDTO depositDTO){
        return transactionService.deposit(depositDTO);

    }

    @PostMapping("/transaction/remittance")
    public ResponseEntity<String> remittance(@RequestBody TransferDTO transferDTO){
        return transactionService.remittance(transferDTO);
    }


}
