package com.dream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DreamApplication implements ApplicationRunner{
//	@Autowired
//	private StorageServiceImpl storageService;
	public static void main(String[] args) {
		SpringApplication.run(DreamApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		storageService.deleteAll();
//		storageService.init();
	}
}
