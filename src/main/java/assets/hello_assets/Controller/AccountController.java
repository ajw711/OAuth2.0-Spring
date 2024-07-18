package assets.hello_assets.Controller;

import assets.hello_assets.DTO.*;
import assets.hello_assets.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/create/general")
    public ResponseEntity<String> createAccountGeneral(@RequestBody AccountGeneralDTO accountGeneralDTO, @AuthenticationPrincipal CustomOAuth2User customOAuth2User){
        accountService.accountSave1(accountGeneralDTO,customOAuth2User);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/deposit")
    public ResponseEntity<String> createAccountDeposit(@RequestBody AccountDepositDTO accountDepositDTO, @AuthenticationPrincipal CustomOAuth2User customOAuth2User){
        accountService.accountSave2(accountDepositDTO,customOAuth2User);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/view/summary")
    public ResponseEntity<ArrayList<AccountSummaryDTO>> getAccountSummary(@AuthenticationPrincipal CustomOAuth2User customOAuth2User){
       ArrayList<AccountSummaryDTO> accountSummaryDTO=accountService.getAccountSummary(customOAuth2User);
        return ResponseEntity.ok(accountSummaryDTO);
    }

    @GetMapping("/view/detail")
    public ResponseEntity<ArrayList<AccountDetailDTO>> getAccountDetail(@AuthenticationPrincipal CustomOAuth2User customOAuth2User){
        ArrayList<AccountDetailDTO> accountDetailDTO=accountService.getAccountDetail(customOAuth2User);
        return ResponseEntity.ok(accountDetailDTO);
    }







}
