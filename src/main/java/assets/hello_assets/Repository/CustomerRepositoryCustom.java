package assets.hello_assets.Repository;

import assets.hello_assets.domain.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<Customer> findUserByRole();
}
