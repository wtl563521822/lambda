package test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Created by wtl on 2018-09-21 9:44
 * @description Stream流操作，Stream是一组用来处理数组、集合的api，有终止操作才能输出
 */
public class Test3 {

    // 不同方式创建流
    // 数组
    public static void gen1() {
        String[] arr = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Stream.of(arr);
        stream.forEach(System.err::println);
    }

    // 集合
    public static void gen2() {
        List<String> list = Arrays.asList("w", "t", "l");
        Stream<String> stream = list.stream();
    }

    // generate
    public static void gen3() {
        // 参数supplier，示例输出1
        Stream<Integer> stream = Stream.generate(() -> 1);
        // 截取10个
        stream.limit(10).forEach(System.err::println);
    }

    // iterate
    public static void gen4() {
        // 2个参数，第一个是初始元素,第二个是UnaryOperator，输入和输出类型一样
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.limit(10).forEach(System.err::println);

    }

    // 其他api创建流
    public static void gen5() throws IOException {
        String str = "abcd";
        // 转出数字
        IntStream intStream = str.chars();
//        intStream.forEach(x -> System.err.println(x));
        intStream.forEach(System.err::println);
        // 文件流
        Files.lines(Paths.get("d:/test.txt")).forEach(System.err::println);
    }


    public static void main(String[] args) throws IOException {
//        gen1();
//        gen3();
//        gen4();
//        gen5();

        /**
         * 各种终止操作
         */
        // 输出集合中的偶数
        Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> x % 2 == 0).forEach(System.err::println);

        // 普通stream转成IntStream再进行过滤求和（偶数和）
        int sum = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
        System.err.println("sum:" + sum);

        // 比较最大
        int max = Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream().max((x, y) -> x - y).get();
        System.err.println("max:" + max);

        int any = Arrays.asList(1, 2, 4, 666, 888, 7).stream().filter(x -> x % 2 == 0).findAny().get();
        System.err.println("any:" + any);
        int first = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 3 == 0).findFirst().get();
        System.err.println("first:" + first);

        // 排序
        Arrays.asList(1, 3, 2, 5, 4, 8, 7).stream().sorted().forEach(System.err::println);
        Arrays.asList(1, 3, 2, 5, 4, 8, 7).stream().sorted((o1, o2) -> o2 - o1).forEach(System.err::println);
        Arrays.asList("java", "php", "c#", "javaScript").stream().sorted((o1, o2) -> o1.length() - o2.length()).forEach(System.err::println);

        // 收集器,1-50里面所有偶数放到一个list集合
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).collect(Collectors.toList());
        System.err.println(list);

        // 去重排序
        Arrays.asList(1, 3, 2, 2, 5, 4, 4).stream().distinct().sorted().forEach(System.err::println);
        Set<Integer> set = Arrays.asList(1, 3, 2, 2, 5, 4, 4).stream().collect(Collectors.toSet());
        System.err.println(set);

        // 截取分页，skip+
        List<Integer> integers = Stream.iterate(1, x -> x + 1).limit(50).skip(0).limit(10).collect(Collectors.toList());
        System.err.println(integers);

        // 转换，字符串转成int并求和
        String str = "11,22,33,44,55";
        sum = Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum();
        sum = Stream.of(str.split(",")).mapToInt(Integer::valueOf).sum();
        System.err.println(sum);

        // 字符串转成对象
        String name = "张三,李四,王五";
        Stream.of(name.split(",")).map(User::new).forEach(System.err::println);

    }

}
