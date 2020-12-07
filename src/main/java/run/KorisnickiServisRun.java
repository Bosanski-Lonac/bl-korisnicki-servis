package run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KorisnickiServisRun {
	
	public static void main(String[] args) {
		SpringApplication.run(KorisnickiServisRun.class, args);
	}
}
