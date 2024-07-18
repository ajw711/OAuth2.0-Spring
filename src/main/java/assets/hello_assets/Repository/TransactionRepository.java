package assets.hello_assets.Repository;

import assets.hello_assets.domain.Account;
import assets.hello_assets.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    void deleteTransactionByToAccount(Account account);

    void deleteTransactionByFromAccount(Account account);
}
