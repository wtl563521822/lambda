package test;

import java.util.Comparator;
import java.util.concurrent.Callable;

/**
 * @author Created by wtl on 2018-09-20 14:44
 * @description 函数式接口
 */
@FunctionalInterface // 只有函数式接口才能用这个注解
public interface UserMapper {

    void insert(User user);// 这是抽象方法

    public int hashCode();// object类中的抽象方法，不算

    // 不算
    default int delete() {
        return 1;
    }

    // 不算
    static int update(){
        return 1;
    };

}
