package assets.hello_assets.Repository;

import assets.hello_assets.domain.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {

     @Transactional
     void deleteByRefresh(String refresh);

     boolean existsByRefresh(String refresh);
}
