package com.spring.security;

import com.spring.security.models.User;
import com.spring.security.repo.UserRepo;
import com.spring.security.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityApplication implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;


	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {


		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		 User user=new User();
		 user.setEmail("binod@gmail.com");
		 user.setUsername("Binod");
		 user.setPassword(this.bCryptPasswordEncoder.encode("@binod"));
		 user.setRole("ROLE_Admin");

		User user1=new User();
		user1.setEmail("ram@gmail.com");
		user1.setUsername("Ram");
		user1.setPassword(this.bCryptPasswordEncoder.encode("@ram"));
		user1.setRole("ROLE_Normal");

		 this.userRepo.save(user);
		this.userRepo.save(user1);
	}
}
