package com.bosanskilonac.ks;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.bosanskilonac.ks", "security", "exceptions"})
public class KorisnickiServisRun {
	
	public static void main(String[] args) {
		SpringApplication.run(KorisnickiServisRun.class, args);
	}
}
