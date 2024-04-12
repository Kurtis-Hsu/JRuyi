package jruyi.util.reflect;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * <h2>类成员工具</h2>
 *
 * @Date 2024-03-21 10:10
 */
public abstract class MemberUtil
{
    private static final int ACCESS_TEST = Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED;

    /**
     * @param modifiers 被检测修饰符
     * @return 指定的修饰符是否表示包私有
     */
    public static boolean isPackagePrivate(int modifiers) { return (modifiers & ACCESS_TEST) == 0; }

    /**
     * @param m 成员对象
     * @return 该成员是否为可访问的
     */
    public static boolean isAccessible(Member m) { return !m.isSynthetic() && Modifier.isPublic(m.getModifiers()); }

    /**
     * <h2>成员对象消费函数</h2>
     *
     * @param <M> 类成员类型
     */
    @FunctionalInterface
    public interface MemberConsumer<M extends Member>
    {
        /**
         * 接收一个类成员对象并执行操作
         *
         * @param m 类成员对象
         */
        void accept(M m) throws IllegalArgumentException, IllegalAccessException;
    }

    /**
     * <h2>成员对象过滤函数</h2>
     *
     * <p>用于遍历时筛选要操作的成员对象</p>
     */
    @FunctionalInterface
    public interface MemberFilter<M extends Member>
    {
        /**
         * @param m 成员对象
         * @return 该成员对象是否要被操作
         */
        boolean apply(M m);
    }
}