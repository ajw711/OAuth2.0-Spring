package assets.hello_assets.Service;

import assets.hello_assets.DTO.AccountAllDTO;
import assets.hello_assets.DTO.CustomerAllDTO;
import assets.hello_assets.Repository.AccountRepository;
import assets.hello_assets.Repository.AnalysisRepository;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.Repository.TransactionRepository;
import assets.hello_assets.domain.Account;
import assets.hello_assets.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AnalysisRepository analysisRepository;



    public ResponseEntity<ArrayList<CustomerAllDTO>> AllList() {
        List<Customer> customerList=customerRepository.findUserByRole();

        ArrayList<CustomerAllDTO> customerAllDTOList=new ArrayList<CustomerAllDTO>();

        for(Customer customer: customerList){
            CustomerAllDTO customerAllDTO=new CustomerAllDTO();

            customerAllDTO.setCustomerName(customer.getCustomerName());
            customerAllDTO.setName(customer.getName());
            customerAllDTO.setEmail(customer.getEmail());
            customerAllDTO.setCreateDate(customer.getCreateDate());
            customerAllDTO.setUpdateDate(customer.getUpdateDate());

            customerAllDTOList.add(customerAllDTO);
        }
        return ResponseEntity.ok(customerAllDTOList);

    }

    public ResponseEntity<ArrayList<AccountAllDTO>> AllAccount() {
        List<Account> accountList=accountRepository.findAll();

        ArrayList<AccountAllDTO> accountAllDTOS=new ArrayList<AccountAllDTO>();

        for(Account account: accountList){
            AccountAllDTO accountAllDTO=new AccountAllDTO();

            accountAllDTO.setAccountNumber(account.getAccountNumber());
            accountAllDTO.setBalance(account.getBalance());
            accountAllDTO.setAccountType(account.getAccountType().toString());
            accountAllDTO.setInterestRate(account.getInterestRate());
            accountAllDTO.setTermMonth(account.getTermMonth());
            accountAllDTO.setCreateDate(account.getCreateDate());
            accountAllDTO.setUpdateDate(account.getUpdateDate());
            accountAllDTO.setCustomerName(account.getCustomer().getName());

            accountAllDTOS.add(accountAllDTO);
        }
        return ResponseEntity.ok(accountAllDTOS);

    }


    @Transactional
    public ResponseEntity<String> deleteUser(String customerName) {
        Customer customer=customerRepository.findByCustomerName(customerName);
        if( customer !=null){
            // 연관된 트랜잭션 삭제
            accountRepository.deleteAccountByCustomer(customer);
            analysisRepository.deleteAnalysisByCustomer(customer);

            customerRepository.deleteByCustomerName(customerName);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteAccount(int accountNumber) {
        Account account=accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            // 연관된 트랜잭션 삭제
            transactionRepository.deleteTransactionByFromAccount(account);
            transactionRepository.deleteTransactionByToAccount(account);
            // 계좌 삭제
            accountRepository.deleteByAccountNumber(accountNumber);
            return new ResponseEntity<>("Account deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }
}
