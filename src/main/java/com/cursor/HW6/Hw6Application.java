package com.cursor.HW6;

import com.cursor.HW6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw6Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Hw6Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // userRepository.updateUser(new User(3L,"Tomas", "Peterson", 35, "male"));
        //	System.out.println(userRepository.findUserByFirstNameAndLastName("Jordan","Peterson"));
        ///	System.out.println(userRepository.getAllUser());
        userRepository.deleteUserById(2L);
    }
}
