package assets.hello_assets.Repository;

import assets.hello_assets.domain.Account;
import assets.hello_assets.domain.AccountType;
import assets.hello_assets.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsAccountByAccountNumber(int number);

    Account findByAccountNumber(int number);

    void deleteByAccountNumber(int accountNumber);

    void deleteAccountByCustomer(Customer customer);




//    Account findByAccountNumberAndBalanceAndAccountType()
}
