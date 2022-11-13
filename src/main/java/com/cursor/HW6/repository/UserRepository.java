package com.cursor.HW6.repository;

import com.cursor.HW6.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

@Repository
public class UserRepository {

    Scanner scanner = new Scanner(System.in);

    @PersistenceContext
    EntityManager entityBaseManager;

    @Transactional
    public void save() {
        System.out.println("Enter your name");
        String userFirstName = scanner.next();

        System.out.println("Enter your lastname");
        String userLastname = scanner.next();

        System.out.println("Enter your age");
        int userAge = scanner.nextInt();

        System.out.println("Enter your gender");
        String userGender = scanner.next();
        entityBaseManager.persist(new User(userFirstName, userLastname, userAge, userGender));
    }

    @Transactional
    public void updateUser() {

        System.out.println("Please enter user id: ");
        long userId = scanner.nextLong();
        User oldUser = findUserById(userId);
        if (oldUser == null) {
            System.out.println("User not found");
            System.exit(0);
        } else {
            System.out.println("Enter new first name for user: ");
            String newUserFirstName = scanner.next();

            System.out.println("Enter new last name for user: ");
            String newUserLastName = scanner.next();

            System.out.println("Enter new age for user: ");
            int newUserAge = scanner.nextInt();

            System.out.println("Enter new gender for user: ");
            String newUserGender = scanner.next();

            entityBaseManager.merge(new User(userId, newUserFirstName, newUserLastName, newUserAge, newUserGender));
        }
    }

    public User findUserById(Long id) {
        return entityBaseManager.find(User.class, id);
    }

    public User findUserByFirstNameAndLastName(String firstName, String lastName) {
        Query selectByFirstNameAndLastName = entityBaseManager.createQuery("SELECT user FROM User user WHERE user.firstName =: firstName AND user.lastName =: lastName");
        selectByFirstNameAndLastName.setParameter("firstName", firstName);
        selectByFirstNameAndLastName.setParameter("lastName", lastName);
        return (User) selectByFirstNameAndLastName.getSingleResult();
    }

    @Transactional
    public void deleteUserByFirstNameAndLastName(String firstName, String lastName) {
        User user = findUserByFirstNameAndLastName(firstName, lastName);
        if (user != null) {
            entityBaseManager.remove(user);
        } else
            System.out.println("specified user not found");
    }

    @Transactional
    public void deleteUserById() {

        System.out.println("Please enter UserId :");
        Long userId = scanner.nextLong();

        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found");
        } else {
            entityBaseManager.remove(user);
            System.out.println("User was deleted");
        }
    }

    @Transactional
    public void getUser() {
        System.out.println("Please enter user id");

        Long userIdForShow = scanner.nextLong();

        User userForShow = findUserById(userIdForShow);
        if (userForShow == null) {
            System.out.println("User not found");
        } else {
            System.out.println(userForShow);
        }
    }

    public List<User> getAllUser() {
        Query selectAllUser = entityBaseManager.createQuery("SELECT user FROM User user");
        return selectAllUser.getResultList();
    }

}
