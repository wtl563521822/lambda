package test;

import javafx.util.Builder;

/**
 * @author Created by wtl on 2018-09-20 15:26
 * @description
 */
public class User {

    private String name;

    public User() {
        System.err.println("这是一个用户无参构造");
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer age) {
        System.err.println("用户年龄：" + age);
    }


    public void eat() {
        System.err.println("eat");
    }

    public void eat(String str) {
        System.err.println("eat:" + str);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
