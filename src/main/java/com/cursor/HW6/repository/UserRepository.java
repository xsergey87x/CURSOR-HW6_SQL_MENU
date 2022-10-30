package com.cursor.HW6.repository;

import com.cursor.HW6.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager entityBaseManager;

    @Transactional
    public void save(User user) {
        entityBaseManager.persist(user);
    }

    @Transactional
    public void updateUser(User user) {
        entityBaseManager.merge(user);
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
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        if (user != null) {
            entityBaseManager.remove(user);
        } else
            System.out.println("specified user not found");
    }

    public List<User> getAllUser() {
        Query selectAllUser = entityBaseManager.createQuery("SELECT user FROM User user");
        return selectAllUser.getResultList();
    }

}
