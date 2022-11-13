package com.cursor.HW6.menu;


import org.springframework.stereotype.Repository;

@Repository
public interface MainMenu {

    void showMenu();
    void exit();
    void showItems(String[] itemsMenu);

}
