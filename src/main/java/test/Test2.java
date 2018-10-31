package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Created by wtl on 2018-09-20 16:30
 * @description 方法的引用
 */
public class Test2 {

    public static void main(String[] args) {
        example4();
    }

    public static String put() {
        return "Hello World";
    }

    public static void com(Integer size) {
        System.err.println("size:" + size);
    }

    /**
     * @author Created by wtl on 2018-9-20 18:14
     * @Description 静态方法引用
     */
    public static void example1() {
        /*===================如果函数式接口的实现恰好通过调用一个静态方法来实现，可以用静态方法引用=====================*/
        Supplier<String> s1 = () -> Test2.put();
        Supplier<String> s2 = Test2::put;
        System.err.println(s1.get());
        System.err.println(s2.get());

        Consumer<Integer> c1 = (size) -> {
            Test2.com(size);
        };
        Consumer<Integer> c2 = Test2::com;
        c1.accept(100);
        c2.accept(500);
        Function<String, String> f1 = (str) -> str.toUpperCase();
        Function<String, String> f2 = String::toUpperCase;
        System.err.println(f1.apply("admin"));
        System.err.println(f2.apply("privilege"));
    }

    public String put2() {
        return "实例方法引用";
    }

    /**
     * @author Created by wtl on 2018-9-20 18:15
     * @Description 实例方法引用
     */
    public static void example2() {
        Supplier<String> s1 = () -> new Test2().put2();
        Supplier<String> s2 = new Test2()::put2;
        System.err.println(s1.get());
        System.err.println(s2.get());
    }


    /**
     * @author Created by wtl on 2018-9-21 9:08
     * @Description 对象方法引用，抽象方法的第一个参数类型（一般是自定义的实体类型的参数，不是string这种）刚好是实例方法的类型，抽象方法剩余的参数恰好可以当做实例方法的参数
     */
    public static void example3() {
        Consumer<User> c1 = (user) -> {
            new User().eat();
        };
        // 第一个参数类型是user，剩余参数没有，符合
        Consumer<User> c2 = User::eat;
        c1.accept(new User());
        c2.accept(new User());

        BiConsumer<User, String> b1 = (user, str) -> {
            new User().eat(str);
        };
        BiConsumer<User, String> b2 = User::eat;
        // 第一个参数是string，实例方法类型是user，不符合
        BiConsumer<String, String> b3 = (str1, str2) -> new User().eat(str2);
        b1.accept(new User(), "水果");
        b1.accept(new User(), "饭");
    }

    /**
     * @author Created by wtl on 2018-9-21 9:32
     * @Description 构造方法的引用，函数式接口的实现恰好可以通过调用一个类的构造方法来实现
     */
    public static void example4() {
        // 无参构造方法
        Supplier<User> u1 = () -> new User();
        Supplier<User> u2 = User::new;
        Supplier<List> u3 = ArrayList::new;
        u1.get();
        u2.get();

        // 有参构造方法
        Consumer<Integer> c1 = (age) -> new User(age);
        Consumer<Integer> c2 = User::new;
        c1.accept(18);
        c2.accept(20);

        Function<String,Integer> f1 = (str) -> Integer.valueOf(str);
        Function<String,Integer> f2 = Integer::new;
        System.err.println(f1.apply("100"));
        System.err.println(f2.apply("200"));
    }


}
