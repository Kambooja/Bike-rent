package com.sha.serverbikemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerBikeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerBikeManagementApplication.class, args);
	}
	static {
		System.out.println("START SERVER");
	}

}