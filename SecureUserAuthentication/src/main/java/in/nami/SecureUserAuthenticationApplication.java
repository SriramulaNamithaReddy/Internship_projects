package in.nami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SecureUserAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureUserAuthenticationApplication.class, args);
	}

}
