package io.sh.linkload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LinkloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkloadApplication.class, args);
	}

}
