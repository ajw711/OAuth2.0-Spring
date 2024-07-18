package assets.hello_assets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HelloAssetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloAssetsApplication.class, args);
	}

}
