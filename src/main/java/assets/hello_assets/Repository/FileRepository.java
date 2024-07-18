package assets.hello_assets.Repository;

import assets.hello_assets.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
