package assets.hello_assets.Repository;

import assets.hello_assets.domain.Analysis;
import assets.hello_assets.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalysisRepository extends JpaRepository<Analysis ,Long> ,AnalysisRepositoryCustom{

    Optional<Analysis> findAnalysisByCustomerId(Long customer_id);

    void deleteAnalysisByCustomer(Customer customer);
}
