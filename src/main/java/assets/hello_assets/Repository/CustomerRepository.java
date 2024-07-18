package assets.hello_assets.Repository;

import assets.hello_assets.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>, CustomerRepositoryCustom {

    Customer findByCustomerName(String customerName);

    void deleteByCustomerName(String customerName);
}
