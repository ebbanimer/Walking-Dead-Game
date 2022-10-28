package com.dt181g.project.model.itemfactory;

public class GetItemFactory {
    public static AbstractFactory getFactory(String choice){
        if("Food".equals(choice)){
            return new FoodFactory();
        } else if ("Zombie".equals(choice)){
            return new ZombieFactory();
        }
        return null;
    }
}
