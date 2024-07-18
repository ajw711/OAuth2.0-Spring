package assets.hello_assets.Service;

import assets.hello_assets.DTO.CustomOAuth2User;
import assets.hello_assets.DTO.DepositDTO;
import assets.hello_assets.DTO.TransferDTO;
import assets.hello_assets.Repository.AccountRepository;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.Repository.TransactionRepository;
import assets.hello_assets.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<String> deposit(DepositDTO depositDTO){

        Account account=accountRepository.findByAccountNumber(depositDTO.getAccountNumber());


        if (!accountCheck(depositDTO.getAccountNumber())){
             return new ResponseEntity<>("accountNumber not exists",HttpStatus.BAD_REQUEST);
        }

        Transaction transaction=new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(depositDTO.getAmount());
        transaction.setToAccount(account);
        transaction.setFromAccount(account);

        transactionRepository.save(transaction);

        account.setBalance(account.getBalance()+depositDTO.getAmount());
        accountRepository.save(account);

        return new ResponseEntity<>("accountNumber OK", HttpStatus.OK);


    }


    public ResponseEntity<String> remittance(TransferDTO transferDTO) {

        Account FromAccount=accountRepository.findByAccountNumber(transferDTO.getFromAccountNumber());
        Account ToAccount=accountRepository.findByAccountNumber(transferDTO.getToAccountNumber());


        if(! accountCheck(ToAccount.getAccountNumber()) && accountCheck(FromAccount.getAccountNumber())){
            return new ResponseEntity<>("accountNumber not exists",HttpStatus.BAD_REQUEST);
        }

        if(! passwordCheck(FromAccount.getAccountNumber(), transferDTO.getPassword())){
            return new ResponseEntity<>("password not matched",HttpStatus.BAD_REQUEST);
        }

        if(! amountCheck(FromAccount.getAccountNumber(), transferDTO.getAmount())){
            return new ResponseEntity<>("not enough amount",HttpStatus.BAD_REQUEST);
        }

        if(! typeCheck(FromAccount.getAccountNumber()) && typeCheck(ToAccount.getAccountNumber()) ){
            return new ResponseEntity<>("type not matched",HttpStatus.BAD_REQUEST);
        }


        Transaction transaction=new Transaction();

        transaction.setTransactionType(TransactionType.REMITTANCE);
        transaction.setAmount(transferDTO.getAmount());
        transaction.setFromAccount(FromAccount);
        transaction.setToAccount(ToAccount);


        transactionRepository.save(transaction);

        FromAccount.setBalance(FromAccount.getBalance()-transferDTO.getAmount());
        accountRepository.save(FromAccount);

        ToAccount.setBalance(ToAccount.getBalance()+transferDTO.getAmount());
        accountRepository.save(ToAccount);


        return new ResponseEntity<>("transfer OK",HttpStatus.OK);

    }

    public boolean accountCheck(int accountNumber){

        return accountRepository.existsAccountByAccountNumber(accountNumber);
    }

    public boolean passwordCheck(int accountNumber,int password){
        Account account=accountRepository.findByAccountNumber(accountNumber);
        return account.getPassword() == password;
    }

    public boolean amountCheck(int accountNumber, int amount){
        Account account=accountRepository.findByAccountNumber(accountNumber);
        return account.getBalance() >= amount;
    }

    public boolean typeCheck(int accountNumber){
        Account account=accountRepository.findByAccountNumber(accountNumber);
        return account.getAccountType()== AccountType.GENERAL;
    }



}
