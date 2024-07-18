package assets.hello_assets.Repository;

import assets.hello_assets.domain.Customer;
import assets.hello_assets.domain.Role;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static assets.hello_assets.domain.QCustomer.customer;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Customer> findUserByRole() {
        return jpaQueryFactory.select(customer)
                .from(customer)
                .where(customer.role.eq(Role.USER))
                .fetch();

    }
}
