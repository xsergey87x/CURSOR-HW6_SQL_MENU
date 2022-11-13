package com.cursor.HW6.menu;

import com.cursor.HW6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Scanner;

@Repository
public class MenuImpl implements MainMenu {

    @Autowired
    private UserRepository userRepository;

    private String[] items = {"""
          _________________
          1. Create user
          _________________
          2. Update user
          _________________
          3. Delete user
          _________________
          4. Show user info
          _________________
          5. Show all users
          _________________
          0. Exit
          _________________
          """};


    @Override
    public void showMenu() {
        showItems(items);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    userRepository.save();
                    break;
                case 2:
                    userRepository.updateUser();
                    break;
                case 3:
                    userRepository.deleteUserById();
                    break;
                case 4:
                    userRepository.getUser();
                    break;
                case 5:
                    userRepository.getAllUser().forEach(System.out::println);
                    break;
                case 0:
                    exit();
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void showItems(String[] items) {
        for (String item : items) {
            System.out.println(item);
        }
    }
}
