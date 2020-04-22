package com.paraparp.gestorfondos;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableBatchProcessing
@EnableScheduling
public class GestorFondosBackendApplication
//implements CommandLineRunner 
{

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestorFondosBackendApplication.class, args);
	}



//	@Override
//	public void run(String... args) throws Exception {
//		String password = "12345";
//		for (int i = 0; i < 4; i++) {
//			String p = passwordEncoder.encode(password);
//			System.out.println(p);
//
//		}
//	}

}
