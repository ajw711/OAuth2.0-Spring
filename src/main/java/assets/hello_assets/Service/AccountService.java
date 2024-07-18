package assets.hello_assets.Service;

import assets.hello_assets.DTO.*;
import assets.hello_assets.Repository.AccountRepository;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.domain.Account;

import assets.hello_assets.domain.AccountType;
import assets.hello_assets.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    public void accountSave1(AccountGeneralDTO accountDTO, CustomOAuth2User customOAuth2User){
        Account account=new Account();

        Customer customer=customerRepository.findByCustomerName(customOAuth2User.getUsername());



        int createNumber=createAccountNumber();
        account.setAccountNumber(createNumber);
        account.setBalance(accountDTO.getBalance());
        account.setPassword(accountDTO.getPassword());
        account.setAccountType(accountDTO.getAccountType());
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    public void accountSave2(AccountDepositDTO accountDepositDTO,CustomOAuth2User customOAuth2User){
        Account account=new Account();

        Customer customer=customerRepository.findByCustomerName(customOAuth2User.getUsername());

        int createNumber=createAccountNumber();
        account.setAccountNumber(createNumber);
        account.setBalance(accountDepositDTO.getBalance());
        account.setPassword(accountDepositDTO.getPassword());
        account.setTermMonth(accountDepositDTO.getTermMonth());
        account.setInterestRate(accountDepositDTO.getInterestRate());
        account.setAccountType(accountDepositDTO.getAccountType());
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    public ArrayList<AccountSummaryDTO> getAccountSummary(CustomOAuth2User customOAuth2User){

        String customerName=customOAuth2User.getUsername();
        Customer customer=customerRepository.findByCustomerName(customerName);

        ArrayList<AccountSummaryDTO> accountArrayList=new ArrayList<AccountSummaryDTO>();

        for(Account account:customer.getAccountList()){
            AccountSummaryDTO accountSummaryDTO=new AccountSummaryDTO();
            accountSummaryDTO.setAccountNumber(account.getAccountNumber());
            accountSummaryDTO.setBalance(account.getBalance());
            accountSummaryDTO.setAccountType(account.getAccountType());

            accountArrayList.add(accountSummaryDTO);
        }

        return accountArrayList;

    }

    public ArrayList<AccountDetailDTO> getAccountDetail(CustomOAuth2User customOAuth2User) {

        String customerName=customOAuth2User.getUsername();
        Customer customer=customerRepository.findByCustomerName(customerName);

        ArrayList<AccountDetailDTO> accountDetailDTOArrayList=new ArrayList<>();

        for(Account account:customer.getAccountList()){

            AccountDetailDTO accountDetailDTO=new AccountDetailDTO();

            accountDetailDTO.setAccountNumber(account.getAccountNumber());
            accountDetailDTO.setBalance(account.getBalance());
            accountDetailDTO.setAccountType(account.getAccountType());
            accountDetailDTO.setCustomerName(customer.getName());
            accountDetailDTO.setCreateDate(account.getCreateDate());

            accountDetailDTOArrayList.add(accountDetailDTO);
        }

        return accountDetailDTOArrayList;
    }







    public int createAccountNumber(){
        SecureRandom secureRandom=new SecureRandom();
        StringBuilder s=new StringBuilder();
        for(int i=0; i<8; i++){
            int randomNumber=secureRandom.nextInt(10);
            s.append(randomNumber);
        }
        String str=s.toString();
        return Integer.parseInt(str);
    }


}
