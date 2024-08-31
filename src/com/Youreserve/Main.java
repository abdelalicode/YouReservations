package com.Youreserve;


import java.util.HashMap;
import java.util.Map;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Person {name=" + name + ", age=" + age + "}";
    }
}
class Odd {
    public int add(int a , int b) {
        return a+b;
    }
}

public class Main {

    public static void main(String[] args) {

        Map<Integer, Person> peopleMap = new HashMap<>();

        peopleMap.put(1, new Person("John Doe", 18));
        peopleMap.put(2, new Person("Jane Doe", 19));
        peopleMap.put(3, new Person("Jack Doe", 20));


        Person person1 = peopleMap.get(1);
        System.out.println(person1);

        for (Map.Entry<Integer, Person> entry : peopleMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
