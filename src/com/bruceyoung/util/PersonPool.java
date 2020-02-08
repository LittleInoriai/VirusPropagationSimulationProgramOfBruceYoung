package com.bruceyoung.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bruceyoung.bean.City;
import com.bruceyoung.bean.Person;
/**
 * 用于管理城市大小和人数
 *
 */
public class PersonPool {
    private static PersonPool personPool = new PersonPool();
    public static PersonPool getInstance(){
        return personPool;
    }

    List<Person> personList = new ArrayList<Person>();

    public List<Person> getPersonList() {
        return personList;
    }

    private PersonPool() {
        City city = new City(400,400);
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            int x = (int) (100 * random.nextGaussian() + city.getCenterX());
            int y = (int) (100 * random.nextGaussian() + city.getCenterY());
            if(x>700){
                x=700;
            }
            Person person = new Person(city,x,y);
            personList.add(person);
        }
    }
}
