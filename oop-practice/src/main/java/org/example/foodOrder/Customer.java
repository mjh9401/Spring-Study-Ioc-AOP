package org.example.foodOrder;

public class Customer {
    public void order(String menuName, Menu menu, Cooking cooking){
        MenuItem menuItem = menu.choose(menuName);
        Cook cook = cooking.makeCook(menuItem);
    }
}
