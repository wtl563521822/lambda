package test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Created by wtl on 2018-09-20 10:35
 * @description
 */
public class Test1 {

    public static void main(String[] args) throws Exception {

    }

    /**
     * 简单的表达式
     */
    public static void simpleLambda() throws Exception {
        /*===================无参无返回值=====================*/
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.err.println("r1");
            }
        };
        r1.run();

        Runnable r2 = () -> {
            System.err.println("r2");
        };
        r2.run();

        /*===================无参有返回值=====================*/
        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "c1";
            }
        };
        Callable<String> c2 = () -> {
            return "c2";
        };
        Callable<String> c3 = () -> "c3";
        System.err.println(c1.call());
        System.err.println(c2.call());
        System.err.println(c3.call());

        /*================有参无返回值========================*/
        UserMapper u1 = new UserMapper() {
            @Override
            public void insert(User user) {
                System.err.println("u1:" + user);
            }
        };
        UserMapper u2 = (user) -> System.err.println("u2:" + user);
        UserMapper u3 = (User user) -> {
            System.err.println("u3:" + user);
        };
        u1.insert(new User());
        u2.insert(new User());
        u3.insert(new User());

        /*===================有参有返回值=====================*/
        OrderMapper o1 = new OrderMapper() {
            @Override
            public String insert(Order order) {
                return "o1";
            }
        };
        OrderMapper o2 = (order) -> "o2";
        System.err.println(o1.insert(new Order()));
        System.err.println(o2.insert(new Order()));

        // 输入输出
        Function<Integer, Integer> f1 = (a) -> {
            int sum = 0;
            for (int i = 0; i < a; i++) {
                sum += i;
            }
            return sum;
        };
        // 输出1加到100
        System.err.println(f1.apply(100));

        // 2个输入一个输出
        BiFunction<String,String,Integer> b1 = (a,b) -> {return a.length()+b.length();};
        System.err.println(b1.apply("hello","world"));
    }


    /**
     * 排序
     */
    public static void listSort() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(100);
        integers.add(10);
        integers.add(1000);

        // 普通排序
//        Collections.sort(integers, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
        Collections.sort(integers, (o1, o2) -> o2 - o1);
        System.err.println(integers);

    }

    /**
     * jdk1.8时间
     */
    public static void timeTest() {
        // 不变的时间对象，jdk1.8特性
        LocalDate localDate = LocalDate.now();
        System.err.println(localDate);
        localDate.plusDays(2);// 加2天，原来的时间不会变
        System.err.println(localDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.err.println(localDateTime);

    }

}
